package com.thuanthanh.StudentProject.Service;


import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    void add(Student student, Integer classid);
    Student update(Student student, Integer id, Integer classid);
    void delete(List<Integer> id);
    Page<Student> searchByCodeAndName(String code, String name, Integer sex, Pageable pageable);
    List<StudentDto> export();
    List<Student> list(String name);
    Boolean saveAll(List<Student> students);
    Boolean save(List<Integer> id);
    Student upload(String name, String address, String birthday,Integer sex,Integer clazz, MultipartFile image);
    void deleteMany(List<Integer> id);

}
