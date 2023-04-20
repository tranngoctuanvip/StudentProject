package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Teacher;

import java.util.List;

public interface TeacherService {
    void add(Teacher teacher, List<Integer> classId,List<Integer> subId);
    Teacher update(Teacher teacher, Integer id);
    void delete(List<Integer> id);
}
