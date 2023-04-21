package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Repository.*;
import com.thuanthanh.StudentProject.Service.StatisticalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatisticalServiceImpl implements StatisticalService {
    public static final Logger logger = LoggerFactory.getLogger(StatisticalServiceImpl.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClassRepository classRepository;
    @Override
    public List<Map<String, Object>> quantitySV() {
        try {
            Map<String,Object> sv = studentRepository.sv();
            if(sv.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            Map<String,Object> svboy = studentRepository.svboy();
            if(svboy.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            Map<String,Object> svgrid = studentRepository.svgrid();
            if(svgrid.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            Map<String,Object> top4under = studentRepository.top4under();
            if(svgrid.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            List<Map<String,Object>> getall = new ArrayList<>();
            getall.add(sv);
            getall.add(svboy);
            getall.add(svgrid);
            getall.add(top4under);
            return getall;
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Map<String, Object>> top5() {
        try{
            List<Map<String,Object>> top5SVpoint = studentRepository.top5point();
            if(top5SVpoint.isEmpty()){
                throw new RuntimeException("không có dữ liệu!");
            }
            return top5SVpoint;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Map<String, Object>> quatity() {
        try {
            Map<String,Object> quatityClass = classRepository.quatityClass();
            if(quatityClass.isEmpty()){
                throw new Exception("Không có dữ liệu");
            }
            Map<String,Object> quatityDepart = departmentRepository.quatitydepart();
            if(quatityDepart.isEmpty()){
                throw new Exception("Không có dữ liệu!");
            }
            Map<String,Object> quatityTeacher = teacherRepository.quatityteacher();
            if(quatityTeacher.isEmpty()){
                throw new Exception("Không có dữ liệu!");
            }
            Map<String,Object> quatitySub = subjectRepository.quatitySub();
            if(quatitySub.isEmpty()){
                throw new Exception("Không có dữ liệu!");
            }
            List<Map<String,Object>> quatity = new ArrayList<>();
            quatity.add(quatityClass);
            quatity.add(quatityDepart);
            quatity.add(quatityTeacher);
            quatity.add(quatitySub);
            return quatity;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Map<String, Object>> quatityThsTS() {
        try {
            Map<String,Object> quatityTh = teacherRepository.quatityThs();
            if(quatityTh.isEmpty()){
                throw new Exception("Không có dữ liệu");
            }
            Map<String,Object> quatityTS = teacherRepository.quatityTS();
            if(quatityTS.isEmpty()){
                throw new Exception("Không có dữ liệu");
            }
            List<Map<String,Object>> quatityThTS = new ArrayList<>();
            quatityThTS.add(quatityTh);
            quatityThTS.add(quatityTS);
            return quatityThTS;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
