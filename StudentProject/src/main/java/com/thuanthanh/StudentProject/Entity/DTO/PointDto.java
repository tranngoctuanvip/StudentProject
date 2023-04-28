package com.thuanthanh.StudentProject.Entity.DTO;

import com.thuanthanh.StudentProject.Entity.Point;
import lombok.Data;

@Data
public class PointDto {
    private Double pointComponent;
    private Double testScore;
    private Double mediumScore;
    private String nameSubject;
    private String codeStudent;
    private String nameStudent;
    public PointDto(Point point){
        this.pointComponent = point.getPointComponent();
        this.testScore = point.getTestScore();
        this.mediumScore = point.getMediumScore();
        this.nameStudent = point.getStudent().getName();
        this.nameSubject = point.getSubject().getName();
        this.codeStudent = point.getStudent().getCode();
    }
}
