package com.thuanthanh.StudentProject.Service;


import com.thuanthanh.StudentProject.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface StudentService {
    void add(Student student, Integer classid);
    Student update(Student student, Integer id, Integer classid);
    void delete(List<Integer> id);
    Page<Student> search();
    Page<Student> searchbycodeandname(String code, String name,Integer sex, Pageable pageable);
    Map<String,Object> searchbysex(Integer sex);
}
