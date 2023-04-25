package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    void add(Department department);
    void update(Department department, Integer id);
    void delete(List<Integer> id);
    List<Map<String,Object>> statis();
}
