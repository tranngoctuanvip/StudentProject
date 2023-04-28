package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Department;
import com.thuanthanh.StudentProject.Repository.DepartmentRepository;
import com.thuanthanh.StudentProject.Service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    public static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    private DepartmentRepository departmentRepository;
    private String prefix = "PH00";
    @Override
    public void add(Department department) {
        try{
            if(!validate(department)){
                Department dp = new Department();
                dp.setId(department.getId());
                departmentRepository.save(dp);
                dp.setCode(prefix+dp.getId());
                dp.setName(department.getName());
                dp.setDescribe(department.getDescribe());
                dp.setStatus(1);
                dp.setDeleted(0);
                dp.setCreatTime(new Date());
                departmentRepository.save(dp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Department department, Integer id) {
        try {
            if(!validate(department)){
                Department dp = departmentRepository.findById(id).get();
                dp.setName(department.getName());
                dp.setDescribe(department.getDescribe());
                dp.setStatus(1);
                dp.setDeleted(0);
                dp.setUpdateTime(new Date());
                departmentRepository.save(dp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(List<Integer> id) {
        try {
            Boolean kt = departmentRepository.existsByIdIn(id);
            if(kt){
                departmentRepository.delete(id);
            }
            else {
                throw new Exception("Không tồn tại Id!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Map<String, Object>> statis() {
        return null;
    }
    private boolean validate(Department department) throws Exception {
            if (department == null) {
                throw new Exception("Không có dữ liệu!");
            }
            if (departmentRepository.existsByName(department.getName())) {
                throw new Exception("Tên phòng đã tồn tại!");
            }
            if(department.getName().isEmpty() || department.getName() == null){
                throw new Exception("Tên phòng không được để trống!");
            }
            if(department.getDescribe().isEmpty() || department.getDescribe() == null){
                throw new Exception("Mô tả không được để trống!");
            }
        return false;
    }
}