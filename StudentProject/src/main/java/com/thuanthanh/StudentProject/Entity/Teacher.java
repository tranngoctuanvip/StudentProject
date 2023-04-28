package com.thuanthanh.StudentProject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code",length = 50)
    private String code;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "note", length = 500)
    private String note;
    @Column(name = "position", length = 150)
    private String position; // chức danh
    @Column(name = "birthDay",length = 100)
    private String birthDay;
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

    @Column(name = "schoolToAttend", length = 500)
    private String schoolToAttend; //trường theo học

    @OneToMany
    @JoinColumn(name = "class_teacher")
    private List<Class> aClass;

    @OneToMany
    @JoinColumn(name = "subject_teacher")
    private  List<Subject> subject;
}
