package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Repository.ClassRepository;
import com.thuanthanh.StudentProject.Service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    public static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);
    @Autowired
    private ClassRepository classRepository;
    private String prefix = "LH00";
    @Override
    public Class add(Class c) {
        try {
            if(!validate(c)){
                Class ca = new Class();
                ca.setId(c.getId());
                classRepository.save(ca);
                ca.setCode(prefix+ca.getId());
                ca.setName(c.getName());
                ca.setQuantity(c.getQuantity());
                ca.setNote(c.getNote());
                ca.setStatus(1);
                ca.setDeleted(0);
                ca.setCreatTime(new Date());
                ca.setDepartment(c.getDepartment());
              return classRepository.save(ca);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
    public Class update(Class c, Integer id) {
        try {
            if(!validate(c)){
                Class ca = classRepository.findById(id).get();
                ca.setName(c.getName());
                ca.setQuantity(c.getQuantity());
                ca.setNote(c.getNote());
                ca.setStatus(1);
                ca.setDeleted(0);
                ca.setUpdateTime(new Date());
                ca.setDepartment(c.getDepartment());
                return classRepository.save(ca);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
    public void delete(List<Integer> id) {
        try {
            Boolean kt = classRepository.existsByIdIn(id);
            if(kt){
                classRepository.delete(id);
            }
            else {
                throw new RuntimeException("Id Không tồn tại!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Page<Class> search(String code, Pageable pageable) {
        try {
            Page<Class> page = classRepository.search(code, pageable);
            if(page == null){
                throw new RuntimeException("Không có dữ liệu!");
            }
            else {
                return page;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public List<Class> list() {
        return classRepository.findAll();
    }

    public boolean validate(Class c) throws Exception {
            if(c == null){
                throw new Exception("Không có dữ liệu!");
            }
            if(c.getName().isEmpty() || c.getName() == null){
                throw new Exception("Tên lớp không được để trống!");
            }
            if(classRepository.existsByName(c.getName())){
                throw new Exception("Tên lớp đã tồn tại!");
            }
            if(c.getQuantity().isEmpty() || c.getQuantity() == null){
                throw new Exception("Số lượng không được để trống!");
            }
            if(classRepository.existsByDepartment(c.getDepartment())){
                throw new Exception("Phòng đã có lớp!");
            }
        return false;
    }
}
