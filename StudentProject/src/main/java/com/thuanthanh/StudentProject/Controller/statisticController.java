package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("statistic")
public class statisticController {
    @Autowired
    private StudentService studentService;
    @GetMapping("top5sv")
    public ResponseEntity<?> top5(){
        try {
            return new ResponseEntity<>(studentService.top5(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("statistic")
    public ResponseEntity<?> get(){
        try {
            return new ResponseEntity<>(studentService.quantitySV(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("top4under")
//    public ResponseEntity<?> topunder(){
//        try{
////            return
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
}
