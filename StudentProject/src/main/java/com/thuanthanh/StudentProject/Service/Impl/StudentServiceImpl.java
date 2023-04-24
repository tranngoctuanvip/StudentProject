package com.thuanthanh.StudentProject.Service.Impl;


import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Repository.ClassRepository;
import com.thuanthanh.StudentProject.Repository.StudentRepository;
import com.thuanthanh.StudentProject.Service.StudentService;
import com.thuanthanh.StudentProject.StringUtil.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    Utils utils = new Utils();
    @Override
    @Transactional
    public void add(Student student, Integer classid) {
        try{
            if(validate(student)){
                Student sd = new Student();
                sd.setCode(student.getCode());
                sd.setName(student.getName());
                sd.setAddress(student.getAddress());
                sd.setBirthDay(student.getBirthDay());
                sd.setStatus(1);
                sd.setDeleted(0);
                sd.setSex(student.getSex());
                sd.setAClass(classRepository.findByIdAndStatusAndDeleted(classid,1,0));
                sd.setCreatTime(new Date());
                studentRepository.save(sd);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Student update(Student student, Integer id,Integer classid) {
        try{
            if(validate(student)){
                Student sd = studentRepository.findById(id).get();
                sd.setName(student.getName());
                sd.setAddress(student.getAddress());
                sd.setBirthDay(student.getBirthDay());
                sd.setStatus(1);
                sd.setDeleted(0);
                sd.setSex(student.getSex());
                sd.setAClass(classRepository.findByIdAndStatusAndDeleted(classid,1,0));
                sd.setUpdateTime(new Date());
                studentRepository.save(sd);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return student;
    }
    @Override
    @Transactional
    public void delete(List<Integer> id) {
        try {
            Boolean kt = studentRepository.existsByIdIn(id);
            if(kt){
                studentRepository.deleted(id);
            }
            else {
                throw new Exception("Không tồn tại Id!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Page<Student> searchbycodeandname(String code, String name, Integer sex, Pageable pageable) {
        try {
            Page<Student> searchbycodeandname = studentRepository.searchbycodeandname(code,name,sex,pageable);
            if(searchbycodeandname.isEmpty()){
                throw new Exception("Không có dữ liệu!");
            }
            return searchbycodeandname;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public boolean validate(Student student){
        try {
            if(student == null){
                throw new Exception("Không có dữ liệu!");
            }
            if(student.getId() == null){
                throw new Exception("Không tồn tại Id!");
            }
            if(studentRepository.existsByCode(student.getCode())){
                throw new Exception("Mã sinh viên đã tồn tại!");
            }
            if(student.getCode().isEmpty() || student.getCode() == null){
                throw new Exception("Mã sinh viên không được bỏ trống!");
            }
            if(student.getName().isEmpty() || student.getName() == null){
                throw new Exception("Tên sinh viên không được để trống!");
            }
            if(student.getAddress().isEmpty() || student.getAddress() == null){
                throw new Exception("Địa chỉ không được để trống!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
