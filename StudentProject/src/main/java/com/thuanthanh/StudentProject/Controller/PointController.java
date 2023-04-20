package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Point;
import com.thuanthanh.StudentProject.Service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("point")
public class PointController {
    @Autowired
    private PointService pointService;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Point point, @Param("subId") Integer subId,@Param("stId") Integer stId){
        try{
            pointService.add(point,subId,stId);
            return ResponseEntity.ok("Add point success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Point point,@Param("id") Integer id, @Param("subId") Integer subId,@Param("stId") Integer stId){
        try{
            pointService.update(point,id,subId,stId);
            return ResponseEntity.ok("update point success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
