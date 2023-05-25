package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Subject;
import com.thuanthanh.StudentProject.Service.Impl.SubjectServiceImpl;
import com.thuanthanh.StudentProject.Service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("subject")
public class SubjectController {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    @Autowired
    private SubjectService subjectService;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Subject subject){
        try{
            subjectService.add(subject);
            return ResponseEntity.ok("Add subject success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Subject subject, @Param("id") Integer id){
        try{
            subjectService.update(subject,id);
            return ResponseEntity.ok("Update subject success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@Param("id")List<Integer> id){
        try{
            subjectService.delete(id);
            return ResponseEntity.ok("Delete subject success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("search")
    public ResponseEntity<?> search(@Param("code") String code, @Param("name") String name,
                                    @RequestParam(defaultValue = "0") int size,
                                    @RequestParam(defaultValue = "5") int limit){
        try {
            Pageable pageable = PageRequest.of(size,limit);
            Page<Subject> page = subjectService.search(code,name, pageable);
            return new ResponseEntity<>(page,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getall")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(subjectService.getAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
