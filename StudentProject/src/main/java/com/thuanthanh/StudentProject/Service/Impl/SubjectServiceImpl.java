package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.DTO.SubjectDto;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    @Autowired
    private SubjectRepository subjectRepository;
    private String prefix = "MH00";
    @Override
    public void add(Subject subject) {
        try{
            if(!validate(subject)){
                Subject sub = new Subject();
                sub.setId(subject.getId());
                subjectRepository.save(sub);
                sub.setCode(prefix+sub.getId());
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
            throw new RuntimeException(e);
        }
    }
    @Override
    public Subject update(Subject subject, Integer id) {
        try {
            if(validate(subject)){
                Subject sub = subjectRepository.findById(id).get();
                sub.setName(subject.getName());
                sub.setNote(subject.getNote());
                sub.setOnly(subject.getOnly());
                sub.setQuantity(subject.getQuantity());
                sub.setUpdateTime(new Date());
                subjectRepository.save(sub);
            }
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
        return subject;
    }
    @Override
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
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Page<Subject> search(String code, String name, Pageable pageable) {
        try {
            Page<Subject> subjectPage = subjectRepository.search(code, name, pageable);
            if(!subjectPage.isEmpty())
                return subjectPage;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
    @Transactional
    public List<SubjectDto> getall() {
        List<Subject> subjects = subjectRepository.findAllByStatusAndDeleted(1,0);
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (Subject subject : subjects){
            subjectDtos.add(new SubjectDto(subject));
        }
        return subjectDtos;
    }

    public boolean validate(Subject subject) throws Exception {
        if(subject == null){
                throw new Exception("Không có dữ liệu!");
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
        return false;
    }
}
