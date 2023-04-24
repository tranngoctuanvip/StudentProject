package com.thuanthanh.StudentProject.Entity.DTO;

import com.thuanthanh.StudentProject.Entity.Class;
import lombok.Data;

@Data
public class StudentDto {
    private String code;
    private String name;
    private String birthDay;
    private Integer sex;
    private String address;
    private Class aClass;
}
