package com.thuanthanh.StudentProject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 150)
    private String code;
    @Column(name = "note", length = 500)
    private String note;
    @Column(name = "quantity", length = 50)
    private String quantity;

    @Column(name = "status", length = 10)
    private Integer status;
    @Column(name = "deleted", length = 10)
    private Integer deleted;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date creatTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    @OneToOne
    @JoinColumn(name = "departmentId")
    private Department department; // phòng

}
