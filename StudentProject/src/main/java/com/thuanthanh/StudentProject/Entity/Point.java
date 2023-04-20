package com.thuanthanh.StudentProject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

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
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject; // môn học

    @Column(name = "pointComponent")
    private Double pointComponent; // điểm thành phần
    @Column(name = "testScore")
    private Double testScore; // điểm thi
    @Column(name="mediumScore") // điểm trung bình
    private Double mediumScore;
}
