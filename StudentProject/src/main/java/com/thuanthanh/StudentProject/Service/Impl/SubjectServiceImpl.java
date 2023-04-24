package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Subject;
import com.thuanthanh.StudentProject.Repository.SubjectRepository;
import com.thuanthanh.StudentProject.Service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    @Transactional
    public void add(Subject subject) {
        try{
            if(validate(subject)){
                Subject sub = new Subject();
                sub.setCode(subject.getCode());
                sub.setName(subject.getName());
                sub.setNote(subject.getNote());
                sub.setOnly(subject.getOnly());
                sub.setQuantity(subject.getQuantity());
                sub.setStatus(1);
                sub.setDeleted(0);
                sub.setCreatTime(new Date());
                subjectRepository.save(sub);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Subject update(Subject subject, Integer id) {
        try {
            if(validate(subject)){
                Subject sub = subjectRepository.findById(id).get();
                sub.setCode(subject.getCode());
                sub.setName(subject.getName());
                sub.setNote(subject.getNote());
                sub.setOnly(subject.getOnly());
                sub.setQuantity(subject.getQuantity());
                sub.setUpdateTime(new Date());
                subjectRepository.save(sub);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    @Override
    @Transactional
    public void delete(List<Integer> id) {
        try {
            Boolean kt = subjectRepository.existsByIdIn(id);
            if(kt){
                subjectRepository.delete(id);
            }
            else {
                throw new Exception("Không tồn tại Id!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Page<Subject> search(String code, String name, Pageable pageable) {
        try {
            Page<Subject> subjectPage = subjectRepository.search(code, name, pageable);
            if(subjectPage.isEmpty()){
                throw new Exception("Không có dữ liệu!");
            }
            return subjectPage;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public boolean validate(Subject subject){
        try {
            if(subject == null){
                throw new Exception("Không có dữ liệu!");
            }
            if(subject.getId() == null){
                throw new Exception("Không tồn tại Id!");
            }
            if(subjectRepository.existsByCode(subject.getCode())){
                throw new Exception("Mã môn học đã tồn tại!");
            }
            if(subject.getCode().isEmpty() || subject.getCode() == null){
                throw new Exception("Mã môn học không được để trống!");
            }
            if(subjectRepository.existsByName(subject.getName())){
                throw new Exception("Tên môn học đã tồn tại!");
            }
            if(subject.getName().isEmpty() || subject.getName() == null){
                throw new Exception("Tên môn học không được để trống!");
            }
            if(subject.getOnly() == null){
                throw  new Exception("Số tín chỉ không được để trống!");
            }
            if(subject.getQuantity() == null){
                throw new Exception("Số tiết không được để trống!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
