package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;

import java.util.List;

public interface studentServices {
    List<Student> liststudent();
    void updatestudent(StudentDto studentDto, Integer id);
}
