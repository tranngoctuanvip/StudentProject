package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Subject;
import com.thuanthanh.StudentProject.Repository.SubjectRepository;
import com.thuanthanh.StudentProject.Service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    public void add(Subject subject) {
        try{
            Subject sub = new Subject();
            sub.setCode(subject.getCode());
            if(subject.getCode().isEmpty() || subject.getCode() == null){
                throw new RuntimeException("Mã môn học không được để trống!");
            }
            sub.setName(subject.getName());
            if(subject.getName().isEmpty() || subject.getName() == null){
                throw new RuntimeException("Tên môn học không được để trống!");
            }
            sub.setNote(subject.getNote());
            sub.setOnly(subject.getOnly());
            if(subject.getOnly().equals("") || subject.getOnly() == null){
                throw  new RuntimeException("Số tín chỉ không được để trống!");
            }
            sub.setQuantity(subject.getQuantity());
            if(subject.getQuantity().equals("") || subject.getQuantity() == null){
                throw new RuntimeException("Số tiết không được để trống!");
            }
            sub.setStatus(1);
            sub.setDeleted(0);
            sub.setCreatTime(new Date());
            subjectRepository.save(sub);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    public Subject update(Subject subject, Integer id) {
        try {
            Boolean s = subjectRepository.existsById(id);
            Subject sub = subjectRepository.findById(id).get();
            if(sub.equals("") || !s){
                throw new RuntimeException("Không có dữ liệu!");
            }
            sub.setCode(subject.getCode());
            if(subject.getCode().isEmpty() || subject.getCode() == null){
                throw new RuntimeException("Mã môn học không được để trống!");
            }
            sub.setName(subject.getName());
            if(subject.getName().isEmpty() || subject.getName() == null){
                throw new RuntimeException("Tên môn học không được để trống!");
            }
            sub.setNote(subject.getNote());
            sub.setOnly(subject.getOnly());
            if(subject.getOnly() == null){
                throw  new RuntimeException("Số tín chỉ không được để trống!");
            }
            sub.setQuantity(subject.getQuantity());
            if(subject.getQuantity() == null){
                throw new RuntimeException("Số tiết không được để trống!");
            }
            sub.setUpdateTime(new Date());
            subjectRepository.save(sub);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    @Override
    public void delete(List<Integer> id) {
        try {
            subjectRepository.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    public List<Subject> search(String code, String name) {
        try {
            return subjectRepository.search(code,name);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
