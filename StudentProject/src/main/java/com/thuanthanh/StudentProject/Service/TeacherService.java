package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Teacher;

import java.util.List;

public interface TeacherService {
    void add(Teacher teacher, List<Integer> classId,List<Integer> subId);
    Teacher update(Teacher teacher, Integer id, List<Integer> classId, List<Integer> subId);
    void delete(List<Integer> id);
    List<Teacher> search(String code, String name, String position);
}
