package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Department;

import java.util.List;

public interface DepartmentService {
    void add(Department department);
    Department update(Department department, Integer id);
    void delete(List<Integer> id);
}
