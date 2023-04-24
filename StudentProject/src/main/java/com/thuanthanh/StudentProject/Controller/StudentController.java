package com.thuanthanh.StudentProject.Controller;


import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Student student, @Param("classid") Integer classid){
        try{
            studentService.add(student,classid);
            return ResponseEntity.ok("Add student success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Student student, @Param("id") Integer id, @Param("classid") Integer classid){
        try {
            studentService.update(student,id,classid);
            return ResponseEntity.ok("Update student success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam List<Integer> id){
        try {
            studentService.delete(id);
            return ResponseEntity.ok("Delete student success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("search")
    public ResponseEntity<?> search(@Param("code") String code, @Param("name") String name){
        try {
            return new ResponseEntity<>(studentService.searchbycodeandname(code,name),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("searchbysex")
    public ResponseEntity<?> searchbysex(@Param("sex") Integer sex){
        try {
            return new ResponseEntity<>(studentService.searchbysex(sex),HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
