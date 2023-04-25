package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Service.ClassService;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Class c){
        try{
            classService.add(c);
            return ResponseEntity.ok("Add class success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody Class c, @Param("id") Integer id){
        try{
            classService.update(c,id);
            return ResponseEntity.ok("Update class success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@Valid @RequestParam List<Integer> id){
        try{
            classService.delete(id);
            return ResponseEntity.ok("Delete class success!");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("search")
    public ResponseEntity<?> search(@Param("code") String code,@RequestParam(defaultValue = "0") int size,
                                    @RequestParam(defaultValue = "5") int limit){
        try{
            Pageable pageable = PageRequest.of(size,limit);
            Page<Class> page = classService.search(code,pageable);
            return new ResponseEntity<>(page,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
