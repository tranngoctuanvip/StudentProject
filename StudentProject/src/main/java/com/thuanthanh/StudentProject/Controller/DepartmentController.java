package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Department;
import com.thuanthanh.StudentProject.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Department department){
        try{
            departmentService.add(department);
            return ResponseEntity.ok("Add department success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Department department, @Param("id") Integer id){
        try {
            departmentService.update(department,id);
            return ResponseEntity.ok("Update department success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam List<Integer> id){
        try {
            departmentService.delete(id);
            return ResponseEntity.ok("Delete department success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
