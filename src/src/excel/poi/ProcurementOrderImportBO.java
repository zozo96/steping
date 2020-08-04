package excel.poi;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : Sonya
 * @date : 2020/7/22 17:45
 */
public class ProcurementOrderImportBO {
    private static final short HEADER_ROW_INDEX = 0;
    private static final short HEADER_INFO_ROW_INDEX = 1;
    private static final short TITLE_ROW_INDEX = 2;
    private static final short ORDER_HEADER_COLUMN_INDEX = 1;
    private static final String REQUIRED_PREFIX = "*";

    /**
     * headers
     */
    private static final String ORDER_HEADER = "采购单基本信息";
    private static final String ORDER_PRODUCT_HEADER = "采购单产品基本信息";
    private static final String ORDER_HEADER_INFO = "注意事项：\n" +
            "1.红色字段为必填字段\n" +
            "2.标有“日期”的字段 需填写日期，格式为 YYYY-MM-DD\n" +
            "4.填写时请查看字段标注\n" +
            "5.多交期 仅支持填写 “是”或“否”\n" +
            "6.多交期填写否，交货日期必填。多交期填写是产品交货日期必填\n" +
            "7.审核流程未填写取系统默认。需填写系统维护流程\n" +
            "8.币种未填写默认人名币，填写其余币种需系统维护币种，若填写外币 且为填写汇率，则取系统维护汇率，若系统维护汇率为0 则无法导入\n" +
            "9.单价类型 仅支持填写 “含税”或“不含税”，产品单价类型根据此处类型输入";
    private static final String ORDER_PRODUCT_HEADER_INFO = "1.类型必填，仅支持填写“产品”或“额外收费项”\n" +
            "2.产品编码必填，若类型为产品请填写系统维护产品产品编码\n" +
            "若类型为额外收费项，请填写额外收费项名称\n" +
            "3.单价根据单价类型判断，若为不含税 则此处填写不含税单价，反之如此。";
    /**
     * titles
     */
    private static final String ERROR_TITLE = "错误信息";
    private static final List<String> PROCUREMENT_ORDER_TITLES = Arrays.asList(
            "序号", "采购单号", "*供应商", "*多交期", "交货日期", "审核流程", "*单价类型", "币种", "汇率",
            "付款条件", "收货地址", "联系人", "电话", "采购单备注"
    );
    private static final List<String> PROCUREMENT_ORDER_PRODUCT_TITLES = Arrays.asList(
            "*类型（产品/额外收费项）", "*产品编码", "产品名称", "*数量", "*单价", "*税率（%）", "产品备注", "产品交货日期"
    );

    private static final Map<String, String> TITLE_COMMENT_MAP = new HashedMap<String, String>();
    static {
        TITLE_COMMENT_MAP.put("序号", "同时导入多条采购单时，根据序号识别订单行。请填写订单序号\n");
        TITLE_COMMENT_MAP.put("采购单号", "采购单号系统唯一，若未填写系统自动生成。\n" + "不可超过40个字符\n");
        TITLE_COMMENT_MAP.put("*供应商", "请填写系统维护的供应商\n");
        TITLE_COMMENT_MAP.put("*多交期", "是/否\n");
        TITLE_COMMENT_MAP.put("交货日期", "统一交期，交货日期必填。\n");
        TITLE_COMMENT_MAP.put("审核流程", "请选择系统维护审核流程，若未选择则取系统默认流程\n");
        TITLE_COMMENT_MAP.put("*单价类型", "含税/不含税\n");
        TITLE_COMMENT_MAP.put("币种", "币种类型为系统维护币种\n");
        TITLE_COMMENT_MAP.put("汇率", "若修改币种请填写对应币种与人民币的汇率\n" + "例如：已选美金，对应汇率填写6.05\n");
        TITLE_COMMENT_MAP.put("付款条件", "请选择系统内维护的付款条件\n");
        TITLE_COMMENT_MAP.put("收货地址", "收货地址、若未填写，则取默认值。\n");
        TITLE_COMMENT_MAP.put("联系人", "联系人、若未填写，则取默认值。\n");
        TITLE_COMMENT_MAP.put("电话", "电话、若未填写，则取默认值。\n");
        TITLE_COMMENT_MAP.put("采购单备注", "备注不得超过140个字符\n");
        TITLE_COMMENT_MAP.put("*类型（产品/额外收费项）", "产品为采购商品。\n" + "额外收费项为该采购单其他收费 例如运费\n" +
                "若为额外收费项，需在产品编码列填写收费项名称，单价列填写金额\n");
        TITLE_COMMENT_MAP.put("*产品编码", "产品编码必填。\n" + "识别唯一性\n");
        TITLE_COMMENT_MAP.put("产品名称", "填写产品名称\n");
        TITLE_COMMENT_MAP.put("*数量", "请填写该物料采购数量\n");
        TITLE_COMMENT_MAP.put("*单价", "请选择单价类型，\n" + "单价请小数位数不得超过6位的正数\n");
        TITLE_COMMENT_MAP.put("*税率%", "税率填写区间为0-100，且小数位数不得超过2位\n");
        TITLE_COMMENT_MAP.put("产品交货日期", "若交期类型位多交期，产品交货日期为必填。\n");
    }

    public static Workbook buildWorkbookTemplate() {
        List<ExtendedFieldDTO> extendedFieldDTOList = Arrays.asList(
                new ExtendedFieldDTO("hah1", 0),
                new ExtendedFieldDTO("hah2", 1),
                new ExtendedFieldDTO("hah3", 0)
        );
        List<ExtendedFieldDTO> productExtendedFieldDTOList = Arrays.asList(
                new ExtendedFieldDTO("ok1", 1),
                new ExtendedFieldDTO("ok2", 0),
                new ExtendedFieldDTO("ok3", 1)
        );
        int customizeOrderFieldSize = CollectionUtils.isEmpty(extendedFieldDTOList) ? 0 : extendedFieldDTOList.size();
        int customizeOrderProductFieldSize = CollectionUtils.isEmpty(productExtendedFieldDTOList) ? 0 : productExtendedFieldDTOList.size();
        int orderProductColumnIndex = ORDER_HEADER_COLUMN_INDEX + PROCUREMENT_ORDER_TITLES.size() + customizeOrderFieldSize;
        // sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("采购单");
        // row
        Row headerRow = sheet.createRow(HEADER_ROW_INDEX);
        Row headerInfoRow = sheet.createRow(HEADER_INFO_ROW_INDEX);
        Row titleRow = sheet.createRow(TITLE_ROW_INDEX);
        headerInfoRow.setHeight((short) 4500);
        // style - header
        CellStyle headerStyle = ExcelTemplateUtils.getTitleCellStyle(workbook);
        headerStyle.setAlignment(HorizontalAlignment.LEFT);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor((short) 1);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 16);
        headerStyle.setFont(font);
        // style - headerInfo
        CellStyle headerInfoStyle = ExcelTemplateUtils.getTitleCellStyle(workbook);
        headerInfoStyle.setAlignment(HorizontalAlignment.LEFT);
        headerInfoStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerInfoStyle.setFillForegroundColor((short) 1);
        // style - titleStyle
        CellStyle titleStyle = ExcelTemplateUtils.getTitleCellStyle(workbook);
        CellStyle requiredTitleCellStyle = ExcelTemplateUtils.getTitleCellStyle(workbook);
        Font requiredFont = workbook.createFont();
        requiredFont.setFontHeightInPoints((short) 14);
        requiredFont.setColor(IndexedColors.RED1.getIndex());
        requiredTitleCellStyle.setFont(requiredFont);
        // header
        ExcelTemplateUtils.generateCell(headerRow, ORDER_HEADER_COLUMN_INDEX, ORDER_HEADER, headerStyle);
        ExcelTemplateUtils.generateCell(headerRow, orderProductColumnIndex, ORDER_PRODUCT_HEADER, headerStyle);
        ExcelTemplateUtils.generateCell(headerInfoRow, ORDER_HEADER_COLUMN_INDEX, ORDER_HEADER_INFO, headerInfoStyle);
        ExcelTemplateUtils.generateCell(headerInfoRow, orderProductColumnIndex, ORDER_PRODUCT_HEADER_INFO, headerInfoStyle);
        // title & comment
        ExcelTemplateUtils.generateCell(titleRow, 0, ERROR_TITLE, titleStyle);
        int i = 1;
        for (String title : PROCUREMENT_ORDER_TITLES) {
            dealTitle(sheet, titleRow, title, requiredTitleCellStyle, titleStyle, i++);
        }
        for (ExtendedFieldDTO extendedField : extendedFieldDTOList) {
            dealExtendedTitle(titleRow, extendedField, requiredTitleCellStyle, titleStyle, i++);
        }
        for (String title : PROCUREMENT_ORDER_PRODUCT_TITLES) {
            dealTitle(sheet, titleRow, title, requiredTitleCellStyle, titleStyle, i++);
        }
        for (ExtendedFieldDTO extendedField : productExtendedFieldDTOList) {
            dealExtendedTitle(titleRow, extendedField, requiredTitleCellStyle, titleStyle, i++);
        }
        // sheet样式拼装
        int totalColumnIndex = orderProductColumnIndex + PROCUREMENT_ORDER_PRODUCT_TITLES.size() + customizeOrderProductFieldSize - 1;
        CellRangeAddress orderInfoHeaderRegion = new CellRangeAddress(HEADER_ROW_INDEX, HEADER_ROW_INDEX, ORDER_HEADER_COLUMN_INDEX, orderProductColumnIndex - 1);
        CellRangeAddress orderProductInfoHeaderRegion = new CellRangeAddress(HEADER_ROW_INDEX, HEADER_ROW_INDEX, orderProductColumnIndex, totalColumnIndex);
        CellRangeAddress orderInfoHeaderInfoRegion = new CellRangeAddress(HEADER_INFO_ROW_INDEX, HEADER_INFO_ROW_INDEX, ORDER_HEADER_COLUMN_INDEX, orderProductColumnIndex - 1);
        CellRangeAddress orderProductInfoHeaderInfoRegion = new CellRangeAddress(HEADER_INFO_ROW_INDEX, HEADER_INFO_ROW_INDEX, orderProductColumnIndex, totalColumnIndex);
        sheet.addMergedRegion(orderInfoHeaderRegion);
        sheet.addMergedRegion(orderInfoHeaderInfoRegion);
        sheet.addMergedRegion(orderProductInfoHeaderRegion);
        sheet.addMergedRegion(orderProductInfoHeaderInfoRegion);
        for (int x = 0; x < totalColumnIndex; x++) {
            sheet.autoSizeColumn(x, false);
        }
        return workbook;
    }

    public static String parseFile(MultipartFile file){
        List<ExtendedFieldDTO> extendedFieldList = Arrays.asList(
                new ExtendedFieldDTO("hah1", 0),
                new ExtendedFieldDTO("hah2", 1),
                new ExtendedFieldDTO("hah3", 0)
        );
        List<ExtendedFieldDTO> productExtendedFieldList = Arrays.asList(
                new ExtendedFieldDTO("ok1", 1),
                new ExtendedFieldDTO("ok2", 0),
                new ExtendedFieldDTO("ok3", 1)
        );
        int customizeOrderFieldSize = CollectionUtils.isEmpty(extendedFieldList) ? 0 : extendedFieldList.size();
        int customizeOrderProductFieldSize = CollectionUtils.isEmpty(productExtendedFieldList) ? 0 : productExtendedFieldList.size();
        int orderProductColumnIndex = ORDER_HEADER_COLUMN_INDEX + PROCUREMENT_ORDER_TITLES.size() + customizeOrderFieldSize;
        int lastIndex = orderProductColumnIndex - 1 + PROCUREMENT_ORDER_PRODUCT_TITLES.size() + customizeOrderProductFieldSize;
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheet("采购单");
            Set<String> orderNumbersSet = new HashSet<String>(sheet.getLastRowNum());
            Row titleRow = sheet.getRow(2);
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || ExcelTemplateUtils.isAllFieldsEmpty(row, 0, lastIndex)) {
                    continue;
                }
                // 序号
                int id = NumberUtils.toInt(ExcelTemplateUtils.getCellValue(row.getCell(1)), -1);
                System.out.println(id);
                // 采购单行
                if (id > 0) {
                    // 采购单信息解析
                    dealOrder(row, extendedFieldList);
                    // 采购单物料行信息解析
                    dealOrderProduct(sheet, id, i, orderProductColumnIndex, lastIndex, productExtendedFieldList);
                    }
                }
            } catch (Exception e) {

            }
        return "success";
    }

    private static void dealOrder(Row row, List<ExtendedFieldDTO> extendedFieldList){
        String orderNumber = ExcelTemplateUtils.getCellValue(row.getCell(2));
        String supplier = ExcelTemplateUtils.getCellValue(row.getCell(3));
        String enableMultiDealDate = ExcelTemplateUtils.getCellValue(row.getCell(4));
        String dealDate = ExcelTemplateUtils.getCellValue(row.getCell(5));
        String approvalProcess = ExcelTemplateUtils.getCellValue(row.getCell(6));
        String priceType = ExcelTemplateUtils.getCellValue(row.getCell(7));
        String currency = ExcelTemplateUtils.getCellValue(row.getCell(8));
        String exchangeRate = ExcelTemplateUtils.getCellValue(row.getCell(9));
        String payCondition = ExcelTemplateUtils.getCellValue(row.getCell(10));
        String receiveAddress = ExcelTemplateUtils.getCellValue(row.getCell(11));
        String contact = ExcelTemplateUtils.getCellValue(row.getCell(12));
        String tel = ExcelTemplateUtils.getCellValue(row.getCell(13));
        String remark = ExcelTemplateUtils.getCellValue(row.getCell(14));

        // 自定义产品字段
        int extendedPtr = 1;
        for (ExtendedFieldDTO extendedField : extendedFieldList) {
            String extendedExcelValue = ExcelTemplateUtils.getCellValue(row.getCell(14 + extendedPtr));
            System.out.println(extendedExcelValue);
            extendedPtr++;
        }
    }

    private static String dealOrderProduct(Sheet sheet,int id, int orderRowIndex, int orderProductColumnIndex, int lastIndex,
                                         List<ExtendedFieldDTO> productExtendedFieldList) {
        int j = orderRowIndex;
        for (; j <= sheet.getLastRowNum(); j++) {
            Row productRow = sheet.getRow(j);
            if (productRow == null) {
                continue;
            }
            if (j != orderRowIndex && (StringUtils.isNotEmpty(ExcelTemplateUtils.getCellValue(productRow.getCell(1))))) {
                break;
            }
            if (ExcelTemplateUtils.isAllFieldsEmpty(productRow, orderProductColumnIndex, lastIndex)) {
                continue;
            }
            String productTypeValue = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex)).trim();
            if (!StringUtils.equals("产品", productTypeValue) && !StringUtils.equals("额外收费项", productTypeValue)) {
                return "序号为" + id + "中存在不支持的产品类型: " + productTypeValue;
            }
            boolean isProduct = StringUtils.equals("产品", productTypeValue);
            if (isProduct) {
                String code = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 1));
                String name = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 2));
                String qty = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 3));
                String price = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 4));
                String tax = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 5));
                String productRemark = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 6));
                // 多交期情况下 必填
                String productDealDate = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 7));

                // 自定义产品字段
                int extendedPtr = 1;
                for (ExtendedFieldDTO extendedField : productExtendedFieldList) {
                    String extendedExcelValue = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 7 + extendedPtr));
                    System.out.println(extendedExcelValue);
                    extendedPtr++;
                }
            } else {
                // todo
                String extraPriceName = ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 2));
                if (StringUtils.isEmpty(extraPriceName)) {
                    return ("序号为" + id + "中存在额外收费项名称为空");
                }
                BigDecimal extraPrice = NumberUtils.toScaledBigDecimal(ExcelTemplateUtils.getCellValue(productRow.getCell(orderProductColumnIndex + 4)));
                if (extraPrice.compareTo(BigDecimal.ZERO) < 0) {
                    return ("序号为" + id + "中存在额外收费项价格错误, 不能为负数");
                }
            }
            // 赋值产品/额外收费项
        }
        return "success";
    }

    private static void dealTitle(XSSFSheet sheet, Row titleRow, String title, CellStyle requiredTitleCellStyle, CellStyle titleStyle, int column){
        String comment = TITLE_COMMENT_MAP.get(title);
        CellStyle cellStyle = title.contains("*") ? requiredTitleCellStyle : titleStyle;
        if (comment != null) {
            ExcelTemplateUtils.generateCellWithComment(sheet, titleRow, column, title, comment, TITLE_ROW_INDEX, cellStyle);
        } else {
            ExcelTemplateUtils.generateCell(titleRow, column, title, cellStyle);
        }
    }

    private static void dealExtendedTitle(Row titleRow, ExtendedFieldDTO extendedField, CellStyle requiredTitleCellStyle, CellStyle titleStyle, int column) {
        String title = extendedField.getRequired() == 0 ? extendedField.getName() : REQUIRED_PREFIX + extendedField.getName();
        ExcelTemplateUtils.generateCell(titleRow, column, title, extendedField.getRequired() == 1 ? requiredTitleCellStyle : titleStyle);
    }

}
