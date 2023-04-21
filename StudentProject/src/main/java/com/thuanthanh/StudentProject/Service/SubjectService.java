package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Subject;

import java.util.List;

public interface SubjectService {
    void add(Subject subject);
    Subject update(Subject subject, Integer id);
    void delete(List<Integer> id);
    List<Subject> search(String code, String name);
}
