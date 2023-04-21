package com.thuanthanh.StudentProject.Service;

import java.util.List;
import java.util.Map;

public interface StatisticalService {
    List<Map<String,Object>> quantitySV();
    List<Map<String,Object>> top5();
    List<Map<String,Object>> quatity();
    List<Map<String,Object>> quatityThsTS();

}
