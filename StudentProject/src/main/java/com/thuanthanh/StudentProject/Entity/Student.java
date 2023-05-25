package com.thuanthanh.StudentProject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Student")
@Data
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 30)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "birthDay", length = 50)
    private String birthDay;
    @Column(name = "sex", length = 10)
    private Integer sex; //0 mặc định nam, 1 mặc định nữ
    @Column(name = "address")
    private String address;

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

    @ManyToOne
    @JoinColumn(name = "classId")
    private Class aClass;

    @Column(name = "image", length = 1000)
    private String image;

}
