package com.thuanthanh.StudentProject.Excel;

import com.thuanthanh.StudentProject.Entity.DTO.PointDto;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PointExcelExport {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<PointDto> pointDtos;

    public PointExcelExport(List<PointDto> pointDtos) {
        this.pointDtos = pointDtos;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("point");
        Row row = sheet.createRow(3);
        Row r = sheet.createRow(2);
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font.setColor(HSSFColor.GREEN.index);
        cellStyle.setFont(font);
        Cell cell = r.createCell(3);
        cell.setCellValue("Điểm sinh viên");
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
        createCell(row,3,"STT",cellStyle);
        createCell(row,4,"Mã sinh viên",cellStyle);
        createCell(row,5,"Tên sinh viên",cellStyle);
        createCell(row,6,"Môn học",cellStyle);
        createCell(row,7,"Điểm thành phần",cellStyle);
        createCell(row,8,"Điểm thi",cellStyle);
        createCell(row,9,"Điểm trung bình",cellStyle);
    }
    private void createCell(Row row, int columnCount, Object value, CellStyle cellStyle){
            sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if(value instanceof Integer){
                cell.setCellValue((Integer) value);
            }
            else if(value instanceof Boolean){
                cell.setCellValue((Boolean) value);
            }
            else if(value instanceof Double){
                cell.setCellValue((Double) value);
            }
            else {
                cell.setCellValue((String) value);
            }
            cell.setCellStyle(cellStyle);
    }
    private void writeDataLine(){
        int rowCount = 4;
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);
        for (PointDto pointDto : pointDtos){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 4;
            for(int i=0;i<pointDtos.size();i++){
                createCell(row,3,pointDtos.size(),cellStyle);
            }
            createCell(row,columnCount++,pointDto.getCodeStudent(),cellStyle);
            createCell(row,columnCount++,pointDto.getNameStudent(),cellStyle);
            createCell(row,columnCount++,pointDto.getNameSubject(),cellStyle);
            createCell(row,columnCount++,pointDto.getPointComponent(),cellStyle);
            createCell(row,columnCount++,pointDto.getTestScore(),cellStyle);
            createCell(row,columnCount++,pointDto.getMediumScore(),cellStyle);
        }
    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLine();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
