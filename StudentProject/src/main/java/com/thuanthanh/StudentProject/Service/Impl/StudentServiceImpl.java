package com.thuanthanh.StudentProject.Service.Impl;


import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Repository.ClassRepository;
import com.thuanthanh.StudentProject.Repository.StudentRepository;
import com.thuanthanh.StudentProject.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    private String prefix = "sv000";
    @Override
    public void add(Student student, Integer classid) {
        try{
            if(!validate(student)){
                Student sd = new Student();
                sd.setId(student.getId());
                studentRepository.save(sd);  // luu ID để gán xuống cho code
                sd.setCode(prefix + sd.getId()); // thêm code tự động
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
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Student update(Student student, Integer id,Integer classid) {
        try{
            if(!validate(student)){
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
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
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
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Page<Student> searchbycodeandname(String code, String name, Integer sex, Pageable pageable) {
        try {
            Page<Student> searchbycodeandname = studentRepository.searchbycodeandname(code,name,sex,pageable);
            if(searchbycodeandname.isEmpty()){
                throw new Exception("Không có dữ liệu!");
            }
            return searchbycodeandname;
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public List<StudentDto> export() {
        try {
           List<Student> students = studentRepository.findAllByStatusAndDeleted(1,0);
           List<StudentDto> studentDtos = new ArrayList<>();
           for (Student student :students){
                studentDtos.add(new StudentDto(student));
           }
            return studentDtos;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    public boolean validate(Student student) throws Exception {
        if(student == null){
            throw new Exception("Không có dữ liệu!");
        }
        if(student.getName().isEmpty() || student.getName() == null){
            throw new Exception("Tên sinh viên không được để trống!");
        }
        if(student.getAddress().isEmpty() || student.getAddress() == null){
            throw new Exception("Địa chỉ không được để trống!");
        }
        return false;
    }
}
