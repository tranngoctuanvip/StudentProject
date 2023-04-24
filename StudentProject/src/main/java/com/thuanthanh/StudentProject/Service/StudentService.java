package com.thuanthanh.StudentProject.Service;


import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    void add(Student student, Integer classid);
    Student update(Student student, Integer id, Integer classid);
    void delete(List<Integer> id);
    Page<Student> search();
    Page<Student> searchbycodeandname(String code, String name, Pageable pageable);
    Map<String,Object> searchbysex(Integer sex);
}
