package excel.poi;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;


/**
 * @author : Sonya
 * @date : 2020/7/22 17:25
 */
@RestController
public class WorkbookController {
    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";

    @PostMapping("/")
    public void download(HttpServletResponse response) {
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=GB2312");
        response.setHeader("Content-disposition", "attachment; filename=" + "采购单Test");
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            Workbook workbook = ProcurementOrderImportBO.buildWorkbookTemplate();
            workbook.write(bos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                System.out.println("close err. " + e.getMessage());
            }
        }
    }

    @PostMapping("/import")
    public String importFile(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return "无文件";
        }
        String originalFilename = file.getOriginalFilename();
        ProcurementOrderImportBO.parseFile(file);
        if (!originalFilename.endsWith(XLS) && !originalFilename.endsWith(XLSX)) {
            return "文件格式错误";
        }
        return "success";
    }

}
