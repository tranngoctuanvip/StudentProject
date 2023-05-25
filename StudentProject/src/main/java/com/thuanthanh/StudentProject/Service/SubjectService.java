package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.DTO.SubjectDto;
import com.thuanthanh.StudentProject.Entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {
    void add(Subject subject);
    Subject update(Subject subject, Integer id);
    void delete(List<Integer> id);
    Page<Subject> search(String code, String name, Pageable pageable);
    List<SubjectDto> getAll();
}
