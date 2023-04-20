package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Teacher;
import com.thuanthanh.StudentProject.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Teacher teacher,
                                 @RequestParam List<Integer> classId,
                                 @RequestParam List<Integer> subId){
        try{
            teacherService.add(teacher,classId,subId);
            return ResponseEntity.ok("Add teacher success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
