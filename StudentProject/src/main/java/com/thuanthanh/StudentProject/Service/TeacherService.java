package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {
    void add(Teacher teacher, List<Integer> classId, List<Integer> subId);
    Teacher update(Teacher teacher, Integer id, List<Integer> classId, List<Integer> subId);
    void delete(List<Integer> id);
    Page<Teacher> search(String code, String name, String position, Pageable pageable);
}
