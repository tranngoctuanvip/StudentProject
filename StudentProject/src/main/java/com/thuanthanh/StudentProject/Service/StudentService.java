package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.Map;

public interface StudentService {
    void add(Student student, Integer classid);
    Student update(Student student, Integer id, Integer classid);
    void delete(List<Integer> id);
    Page<Student> search();
    List<Student> searchbycodeandname(String code, String name);
}
