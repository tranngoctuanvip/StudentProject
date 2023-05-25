package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Repository.*;
import com.thuanthanh.StudentProject.Service.StatisticalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            if(!validate()){
                Map<String,Object> sv = studentRepository.sv();
                Map<String,Object> svboy = studentRepository.svboy();
                Map<String,Object> svgrid = studentRepository.svgrid();
                Map<String,Object> top4under = studentRepository.top4under();
                List<Map<String,Object>> getall = new ArrayList<>();
                getall.add(sv);
                getall.add(svboy);
                getall.add(svgrid);
                getall.add(top4under);
                return getall;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public List<Map<String, Object>> top5() {
        try{
            if(!validate()){
                List<Map<String,Object>> top5SVpoint = studentRepository.top5point();
                return top5SVpoint;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
    public List<Map<String, Object>> quatity() {
        try {
            if(!validate()){
                Map<String,Object> quatityClass = classRepository.quatityClass();
                Map<String,Object> quatityDepart = departmentRepository.quatitydepart();
                Map<String,Object> quatityTeacher = teacherRepository.quatityteacher();
                Map<String,Object> quatitySub = subjectRepository.quatitySub();
                List<Map<String,Object>> quatity = new ArrayList<>();
                quatity.add(quatityClass);
                quatity.add(quatityDepart);
                quatity.add(quatityTeacher);
                quatity.add(quatitySub);
                return quatity;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public List<Map<String, Object>> quatityThsTS() {
        try {
            if(!validate()){
                Map<String,Object> quatityTh = teacherRepository.quatityThs();
                Map<String,Object> quatityTS = teacherRepository.quatityTS();
                List<Map<String,Object>> quatityThTS = new ArrayList<>();
                quatityThTS.add(quatityTh);
                quatityThTS.add(quatityTS);
                return quatityThTS;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public boolean validate() throws Exception {
        if(studentRepository.sv() == null){
            throw new Exception("Không tìm thấy dữ liệu sinh viên!");
        }
        if(studentRepository.svboy() == null){
            throw new RuntimeException("Không tìm thấy dữ liệu sinh viên nam!");
        }
        if(studentRepository.svgrid() == null){
            throw new RuntimeException("Không tìm thấy dữ liệu sinh viên nữ!");
        }
        if(studentRepository.top4under() == null){
            throw new RuntimeException("Không tìm thấy dữ liệu sinh viên bị điểm dưới 4!");
        }
        if(studentRepository.top5point() == null){
            throw new RuntimeException("không tìm thấy top 5 sinh viên có ĐTB cao nhất!");
        }
        if(classRepository.quatityClass() == null){
            throw new Exception("Không tìm thấy số lượng lớp học");
        }
        if(departmentRepository.quatitydepart() == null){
            throw new Exception("Không tìm thấy số lượng phòng!");
        }
        if(teacherRepository.quatityteacher() == null){
            throw new Exception("Không tìm thấy số lượng giáo viên!");
        }
        if(subjectRepository.quatitySub() == null){
            throw new Exception("Không tìm thấy số lượng môn học!");
        }
        if(teacherRepository.quatityThs() == null){
            throw new Exception("Không tìm thấy số lượng thạc sĩ!");
        }
        if(teacherRepository.quatityTS() == null){
            throw new Exception("Không tìm thấy số lượng tiến sĩ!");
        }
        return false;
    }
}
