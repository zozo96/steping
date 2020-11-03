package excel.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Sonya
 * @date : 2020/7/22 17:26
 */
public class ExcelTemplateUtils {

    public static CellStyle getTitleCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

    public static CellStyle getNoBorderDateCellStyle(Workbook workbook, String formatStyle) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat(formatStyle));
        return style;
    }

    public static void generateCell(Row sheetRow, int column, String value, CellStyle cellStyle) {
        Cell cell = sheetRow.createCell((short) column);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(StringUtils.trimToEmpty(value));
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    public static void generateCellWithComment(XSSFSheet sheet, Row sheetRow, int column, String value, String common,
                                               int commentRow, CellStyle cellStyle) {
        Cell cell = sheetRow.createCell((short) column);
        XSSFRichTextString text = new XSSFRichTextString(value);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(text);

        XSSFDrawing patr = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        XSSFComment comment = patr.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, column, commentRow, 3, 3));
        // 设置注释内容
        comment.setString(new XSSFRichTextString(common));
        // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("Sonya.");
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    public static boolean isAllFieldsEmpty(Row row, int start, int end) {
        boolean isEmpty = true;
        for (int i = start; i < end; i++) {
            Cell cell = row.getCell(i);
            if (StringUtils.isNotEmpty(ExcelTemplateUtils.getCellValue(cell))) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public static String getCellValue(Cell cell) {
        String result = "";
        if (cell == null) {
            return result;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                result = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    result = DateUtils.getFormatDateTime(cell.getDateCellValue());
                    if (result == null) {
                        result = DateUtils.getFormatDateTime(new Date());
                    }
                } else {
                    result = BigDecimal.valueOf(cell.getNumericCellValue()).stripTrailingZeros().toPlainString();
                }
                break;
            default:
                break;
        }
        return StringUtils.trimToEmpty(result);
    }
}
