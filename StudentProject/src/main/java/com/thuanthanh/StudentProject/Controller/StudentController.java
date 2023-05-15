package com.thuanthanh.StudentProject.Controller;


import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Excel.StudentExcelExport;
import com.thuanthanh.StudentProject.PDF.StudentPDF;
import com.thuanthanh.StudentProject.Repository.studentServices;
import com.thuanthanh.StudentProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private studentServices studentServices;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Student student, @Param("classid") Integer classid){
        try{
            studentService.add(student,classid);
            return ResponseEntity.ok("Add student success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Student student, @Param("id") Integer id, @Param("classid") Integer classid){
        try {
            studentService.update(student,id,classid);
            return ResponseEntity.ok("Update student success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam List<Integer> id){
        try {
            studentService.delete(id);
            return ResponseEntity.ok("Delete student success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("search")
    public ResponseEntity<?> search(@Param("code") String code, @Param("name") String name,
                                                @RequestParam(defaultValue = "0") Integer sex,
                                                @RequestParam(defaultValue = "0") int size,
                                                @RequestParam(defaultValue = "5") int limit){
        try {
            Pageable pageable = PageRequest.of(size,limit);
            return new ResponseEntity<>(studentService.searchbycodeandname(code,name,sex,pageable),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("saveAll")
    public ResponseEntity<Boolean> saveAll(@RequestBody List<Student> students){
        try {
            return new ResponseEntity<>(studentService.saveAll(students),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("export/XLSX")
    public ResponseEntity<?> export(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=student_" + currentDateTime+ ".xlsx";
            response.setHeader(headerKey,headerValue);
            List<StudentDto> export = studentService.export();
            StudentExcelExport studentExcelExport = new StudentExcelExport(export);
            studentExcelExport.export(response);
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("export/PDF")
    public ResponseEntity<?> exportPDF(HttpServletResponse response){
        try {
            response.setContentType("application/pdf");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=student_" +currentDateTime +".pdf";
            response.setHeader(headerKey,headerValue);
            List<StudentDto> export = studentService.export();
            StudentPDF studentPDF = new StudentPDF(export);
            studentPDF.export(response);
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("list")
    public ResponseEntity<?> list(){
        try {
            return new ResponseEntity<>(studentServices.liststudent(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("list2")
    public ResponseEntity<?> list2(@Param("name") String name){
        return new ResponseEntity<>(studentService.list(name),HttpStatus.OK);
    }
    @PostMapping("uploadfile")
    public ResponseEntity<Student> upload(@RequestParam String name,@RequestParam String address,@RequestParam String birthday, @RequestParam MultipartFile image) {
        try {
            return new ResponseEntity<>(studentService.upload(name,address,birthday,image),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
