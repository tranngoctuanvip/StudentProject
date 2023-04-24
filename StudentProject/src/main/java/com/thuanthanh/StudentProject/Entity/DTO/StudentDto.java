package com.thuanthanh.StudentProject.Entity.DTO;

import com.thuanthanh.StudentProject.Entity.Class;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String code;
    private String name;
    private String birthDay;
    private Integer sex;
    private String address;
    private List<Class> aClass;
}
