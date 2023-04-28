package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.DTO.PointDto;
import com.thuanthanh.StudentProject.Excel.PointExcelExport;
import com.thuanthanh.StudentProject.Entity.Point;
import com.thuanthanh.StudentProject.Service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Point point,@Param("id") Integer id, @Param("subId") Integer subId,@Param("stId") Integer stId){
        try{
            pointService.update(point,id,subId,stId);
            return ResponseEntity.ok("update point success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam List<Integer> id){
        try {
            pointService.delete(id);
            return ResponseEntity.ok("Delete point success!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("export")
    public ResponseEntity<?> export(HttpServletResponse response){
        try{
            response.setContentType("application/octet-stream");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = point_"+currentDateTime+".xlsx";
            response.setHeader(headerKey,headerValue);
            List<PointDto> export = pointService.export();
            PointExcelExport pointExcelExport =  new PointExcelExport(export);
            pointExcelExport.export(response);
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
