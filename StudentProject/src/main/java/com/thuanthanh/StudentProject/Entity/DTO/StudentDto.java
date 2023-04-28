package com.thuanthanh.StudentProject.Entity.DTO;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Entity.Student;
import lombok.Data;
@Data
public class StudentDto {
    private String code;
    private String name;
    private String birthDay;
    private Integer sex;
    private String address;
    private String className;
    public StudentDto(Student student){
        this.code = student.getCode();
        this.name = student.getName();
        this.birthDay = student.getBirthDay();
        this.sex = student.getSex();
        this.address = student.getAddress();
        this.className = student.getAClass().getName();
    }
}
