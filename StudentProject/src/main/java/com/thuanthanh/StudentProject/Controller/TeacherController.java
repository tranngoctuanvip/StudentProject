package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Teacher;
import com.thuanthanh.StudentProject.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Teacher teacher,
                                 @Param("id") Integer id,
                                 @RequestParam List<Integer> classId,
                                 @RequestParam List<Integer> subId){
        try{
            teacherService.update(teacher,id,classId,subId);
            return ResponseEntity.ok("Update teacher success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam List<Integer> id){
        try {
            teacherService.delete(id);
            return ResponseEntity.ok("Delete teacher success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("search")
    public ResponseEntity<?> search(@Param("code") String code, @Param("name") String name,
                                                @Param("position") String position,
                                                @RequestParam(defaultValue = "0") int size,
                                                @RequestParam(defaultValue = "5") int limit){
        try {
            Pageable pageable = PageRequest.of(size,limit);
            return new ResponseEntity<>(teacherService.search(code,name,position,pageable),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
