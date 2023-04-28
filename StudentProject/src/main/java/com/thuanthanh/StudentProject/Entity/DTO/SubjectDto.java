package com.thuanthanh.StudentProject.Entity.DTO;

import com.thuanthanh.StudentProject.Entity.Subject;
import lombok.Data;

@Data
public class SubjectDto {
    private String code;
    private String name;
    private Integer only;
    private Integer quantity;
    private String note;
    public SubjectDto(Subject subject){
        this.code = subject.getCode();
        this.name = subject.getName();
        this.only = subject.getOnly();
        this.quantity = subject.getQuantity();
        this.note = subject.getNote();
    }
}
