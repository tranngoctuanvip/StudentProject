package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Repository.ClassRepository;
import com.thuanthanh.StudentProject.Service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    public static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);
    @Autowired
    private ClassRepository classRepository;
    @Override
    public void add(Class c) {
        try{
            Class ca = new Class();
            ca.setCode(c.getCode());
            if(classRepository.existsByCode(c.getCode())){
                throw new RuntimeException("Mã lớp đã tồn tại!");
            }
            if(c.getCode().isEmpty() || c.getCode() == null){
                throw new RuntimeException("Tên mã lớp không được để trống!");
            }
            ca.setQuantity(c.getQuantity());
            if(c.getQuantity().isEmpty() || c.getQuantity() == null){
                throw new RuntimeException("Số lượng không được để trống!");
            }
            ca.setNote(c.getNote());
            ca.setStatus(1);
            ca.setDeleted(0);
            ca.setCreatTime(new Date());
            ca.setDepartment(c.getDepartment());
            if(classRepository.existsByDepartment(c.getDepartment())){
                throw new RuntimeException("Phòng đã có lớp học!");
            }
            classRepository.save(ca);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Class update(Class c, Integer id) {
        try{
            Class ca = classRepository.findById(id).get();
            ca.setCode(c.getCode());
            if(classRepository.existsByCode(c.getCode())){
                throw new RuntimeException("Mã lớp đã tồn tại!");
            }
            if(c.getCode().isEmpty() || c.getCode() == null){
                throw new RuntimeException("Tên mã lớp không được để trống!");
            }
            ca.setQuantity(c.getQuantity());
            if(c.getQuantity().isEmpty() || c.getQuantity() == null){
                throw new RuntimeException("Số lượng không được để trống!");
            }
            ca.setNote(c.getNote());
            ca.setStatus(1);
            ca.setDeleted(0);
            ca.setUpdateTime(new Date());
            ca.setDepartment(c.getDepartment());
            if(classRepository.existsByDepartment(c.getDepartment())){
                throw new RuntimeException("Phòng đã có lớp!");
            }
            classRepository.save(ca);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(List<Integer> id) {
        try {
            classRepository.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
