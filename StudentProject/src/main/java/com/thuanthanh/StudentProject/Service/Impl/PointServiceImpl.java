package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Constant.Constant;
import com.thuanthanh.StudentProject.Entity.DTO.PointDto;
import com.thuanthanh.StudentProject.Entity.Point;
import com.thuanthanh.StudentProject.Repository.PointRepository;
import com.thuanthanh.StudentProject.Repository.StudentRepository;
import com.thuanthanh.StudentProject.Repository.SubjectRepository;
import com.thuanthanh.StudentProject.Service.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class PointServiceImpl implements PointService {
    public static final Logger logger = LoggerFactory.getLogger(PointServiceImpl.class);
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    public Point add(Point point, Integer subId, Integer stId) {
        try {
            Point p = new Point();
            p.setStudent(studentRepository.getall(stId));
            p.setSubject(subjectRepository.getall(subId));
            p.setPointComponent(point.getPointComponent());
            p.setTestScore(point.getTestScore());
            p.setMediumScore((point.getPointComponent()*3 + point.getTestScore()*7)/10);
            p.setDeleted(Constant.INACTIVE_STATUS);
            p.setStatus(Constant.ACTIVE_STATUS);
            p.setCreatTime(new Date());
            return pointRepository.save(p);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Point update(Point point, Integer id, Integer subId, Integer stId) {
        try {
            if(!pointRepository.existsById(id)){
                throw new Exception("ID không tồn tại!");
            }
                Point p = pointRepository.findById(id).get();
                p.setStudent(studentRepository.getall(stId));
                p.setSubject(subjectRepository.getall(subId));
                p.setPointComponent(point.getPointComponent());
                p.setTestScore(point.getTestScore());
                p.setMediumScore((point.getPointComponent()*3 + point.getTestScore()*7)/10);
                p.setUpdateTime(new Date());
                pointRepository.save(p);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
    public void delete(List<Integer> id) {
        try{
            Boolean kt = pointRepository.existsByIdIn(id);
            if(kt){
                pointRepository.delete(id);
            }
            else {
                throw new Exception("Không tồn tại Id!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public List<PointDto> export() {
        try {
            List<Point> points = pointRepository.export();
            if(!points.isEmpty()){
                List<PointDto> pointDtos = new ArrayList<>();
                for(Point point : points){
                    pointDtos.add(new PointDto(point));
                }
                return  pointDtos;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public boolean validate(){
        return false;
    }
}
