package com.thuanthanh.StudentProject.Excel;

import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentExcelExport {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<StudentDto> studentList;
    public StudentExcelExport(List<StudentDto> studentList) {
        this.studentList = studentList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("student");
        Row row = sheet.createRow(3);
        Row row2 = sheet.createRow(2);
        CellStyle cellstyle =  workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellstyle.setFont(font);
        font.setColor((HSSFColor.GREEN.index));
        Cell cell = row2.createCell(4);
        cell.setCellValue("Danh sách sinh viên");
        cellstyle.setAlignment(HorizontalAlignment.CENTER);
        cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellstyle);
        // merge cell
        sheet.addMergedRegion(new CellRangeAddress(2,2,4,9));
        createCell(row,3,"STT",cellstyle);
        createCell(row,4,"code",cellstyle);
        createCell(row,5,"name",cellstyle);
        createCell(row,6,"birthday",cellstyle);
        createCell(row,7,"sex",cellstyle);
        createCell(row,8,"address",cellstyle);
        createCell(row,9,"class",cellstyle);
    }
    private void createCell(Row row,int columnCount, Object value,CellStyle cellStyle){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if( value instanceof Integer){
            cell.setCellValue((Integer) value);
        }
        else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(cellStyle);
    }
    private void writeDataLines(){
       int rowCount = 4;
       CellStyle style  = workbook.createCellStyle();
       XSSFFont font = workbook.createFont();
       font.setFontHeight(14);
       style.setFont(font);
       for(StudentDto student : studentList){
           Row row = sheet.createRow(rowCount++);
           int columnCount = 4;
           createCell(row, columnCount++,student.getCode(),style);
           createCell(row,columnCount++,student.getName(),style);
           createCell(row,columnCount++,student.getBirthDay(),style);
           switch (student.getSex()){
               case 1:
                   createCell(row,columnCount++,"Nữ",style);
                   break;
               case 0:
                   createCell(row,columnCount++,"Nam",style);
                   break;
           }
           createCell(row,columnCount++,student.getAddress(),style);
           createCell(row,columnCount++,student.getClassName(),style);
       }
    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
