package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Point;
import com.thuanthanh.StudentProject.Repository.PointRepository;
import com.thuanthanh.StudentProject.Repository.StudentRepository;
import com.thuanthanh.StudentProject.Repository.SubjectRepository;
import com.thuanthanh.StudentProject.Service.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void add(Point point, Integer subId, Integer stId) {
        try {
            Point p = new Point();
            p.setStudent(studentRepository.getall(stId));
            p.setSubject(subjectRepository.getall(subId));
            p.setPointComponent(point.getPointComponent());
            p.setTestScore(point.getTestScore());
            p.setMediumScore((point.getPointComponent()*3 + point.getTestScore()*7)/10);
            p.setDeleted(0);
            p.setStatus(1);
            p.setCreatTime(new Date());
            pointRepository.save(p);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    public Point update(Point point, Integer id, Integer subId, Integer stId) {
        try {
            if(pointRepository.existsById(id)){
                Point p = pointRepository.findById(id).get();
                p.setStudent(studentRepository.getall(stId));
                p.setSubject(subjectRepository.getall(subId));
                p.setPointComponent(point.getPointComponent());
                p.setTestScore(point.getTestScore());
                p.setMediumScore((point.getPointComponent()*3 + point.getTestScore()*7)/10);
                p.setDeleted(0);
                p.setStatus(1);p.setUpdateTime(new Date());
                pointRepository.save(p);
            }
            else {
                throw new Exception("Không tồn tại Id!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return point;
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
            throw new RuntimeException(e);
        }
    }
}
