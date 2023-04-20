package com.thuanthanh.StudentProject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 100)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "only", length = 10)
    private Integer only; // tín chỉ
    @Column(name = "quantity", length = 10)
    private Integer quantity;  // số tiết
    @Column(name = "note", length = 500)
    private String note;
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
}
