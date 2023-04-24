package com.thuanthanh.StudentProject.Service;


import com.thuanthanh.StudentProject.Entity.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassService {
    void add(Class c);
    Class update(Class c, Integer id);
    void delete(List<Integer> id);
    Page<Class> search(String code, Pageable pageable);
}
