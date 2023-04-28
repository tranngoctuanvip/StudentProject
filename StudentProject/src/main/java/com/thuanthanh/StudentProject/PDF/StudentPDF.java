package com.thuanthanh.StudentProject.PDF;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StudentPDF {
    private List<StudentDto> studentDtos;

    public StudentPDF(List<StudentDto> studentDtos) {
        this.studentDtos = studentDtos;
    }
    private void writeTableHeader(PdfPTable pTable){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("code",font));
        pTable.addCell(cell);
        cell.setPhrase(new Phrase("Mã sinh viên",font));
        pTable.addCell(cell);
        cell.setPhrase(new Phrase("Năm sinh",font));
        pTable.addCell(cell);
        cell.setPhrase(new Phrase("Giới tính",font));
        pTable.addCell(cell);
        cell.setPhrase(new Phrase("Địa chỉ",font));
        pTable.addCell(cell);
        cell.setPhrase(new Phrase("Lớp",font));
        pTable.addCell(cell);
    }
    private void writeTableData(PdfPTable pTable){
        for (StudentDto studentDto : studentDtos){
            pTable.addCell(studentDto.getCode());
            pTable.addCell(studentDto.getName());
            pTable.addCell(studentDto.getBirthDay());
            switch (studentDto.getSex()){
                case 0:
                    pTable.addCell("Nam");
                    break;
                case 1:
                    pTable.addCell("Nữ");
                    break;
            }
            pTable.addCell(studentDto.getAddress());
            pTable.addCell(studentDto.getClassName());
        }
    }
    public void export(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        font.setColor(Color.BLUE);
        Paragraph p = new Paragraph("Danh sách sinh viên",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f,2.5f,2.0f,2.0f,1.5f,1.5f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
