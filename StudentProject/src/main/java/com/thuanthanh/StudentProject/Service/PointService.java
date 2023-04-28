package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.DTO.PointDto;
import com.thuanthanh.StudentProject.Entity.Point;

import java.util.List;

public interface PointService {
    void add(Point point, Integer subId, Integer stId);
    Point update(Point point, Integer id,Integer subId, Integer stId);
    void delete(List<Integer> id);
    List<PointDto> export();
}
