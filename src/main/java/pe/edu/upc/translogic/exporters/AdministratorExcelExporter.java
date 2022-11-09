package pe.edu.upc.translogic.exporters;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pe.edu.upc.translogic.entities.Administrator;

public class AdministratorExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Administrator> admins;

    public AdministratorExcelExporter(List<Administrator> admins) {
        this.admins = admins;
        workbook = new XSSFWorkbook();
    }

    public void CreateCell(Row row, int column, Object value, CellStyle style) {
        sheet.autoSizeColumn(column);
        Cell cell = row.createCell(column);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    public void writeHeaderLine() {
        sheet = workbook.createSheet("Reporte");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        CreateCell(row, 0, "id", style);
        CreateCell(row, 1, "names", style);
        CreateCell(row, 2, "surnames", style);
        CreateCell(row, 3, "email", style);
        CreateCell(row, 4, "phone", style);
        CreateCell(row, 5, "nickname", style);
        CreateCell(row, 6, "password", style);
    }

    public void writeDataLines() {

        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        for (Administrator admin : admins) {
            Row row = sheet.createRow(rowCount);
            int colCount = 0;
            CreateCell(row, colCount, admin.getId(), style);
            CreateCell(row, colCount + 1, admin.getNames(), style);
            CreateCell(row, colCount + 2, admin.getSurnames(), style);
            CreateCell(row, colCount + 3, admin.getEmail(), style);
            CreateCell(row, colCount + 4, admin.getPhone(), style);
            CreateCell(row, colCount + 5, admin.getNickname(), style);
            CreateCell(row, colCount + 6, admin.getPassword(), style);

            rowCount++;
        }
    }

    public void writeFooterLine() {

    }

    public void export(HttpServletResponse response) throws IOException {

        writeHeaderLine();
        writeDataLines();
        writeFooterLine();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }

}
