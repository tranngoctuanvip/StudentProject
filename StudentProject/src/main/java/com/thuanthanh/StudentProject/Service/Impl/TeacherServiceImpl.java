package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Entity.Subject;
import com.thuanthanh.StudentProject.Entity.Teacher;
import com.thuanthanh.StudentProject.Repository.ClassRepository;
import com.thuanthanh.StudentProject.Repository.SubjectRepository;
import com.thuanthanh.StudentProject.Repository.TeacherRepository;
import com.thuanthanh.StudentProject.Service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {
    public static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ClassRepository classRepository;
    @Override
    @Transactional
    public void add(Teacher teacher,List<Integer> classId, List<Integer> subId) {
        try {
            if(validate(teacher)){
                Teacher t = new Teacher();
                t.setCode(teacher.getCode());
                t.setName(teacher.getName());
                t.setAddress(teacher.getAddress());
                t.setBirthDay(teacher.getBirthDay());
                t.setPosition(teacher.getPosition());
                t.setSchoolToAttend(teacher.getSchoolToAttend());
                t.setStatus(1);
                t.setCreatTime(new Date());
                t.setDeleted(0);
                List<Subject> sub = subjectRepository.findByIdIn(subId);
                t.setSubject(sub);
                if(sub.isEmpty()){
                    throw new Exception("Không được để trống thông tin!");
                }
                List<Class> cls = classRepository.findByIdIn(classId);
                t.setAClass(cls);
                if(cls.isEmpty()){
                    throw new Exception("Không được để trống thông tin!");
                }
                teacherRepository.save(t);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Teacher update(Teacher teacher, Integer id, List<Integer> classId, List<Integer> subId) {
        try {
            if(validate(teacher)){
                Teacher t = teacherRepository.findById(id).get();
                t.setCode(teacher.getCode());
                t.setName(teacher.getName());
                t.setAddress(teacher.getAddress());
                t.setBirthDay(teacher.getBirthDay());
                t.setPosition(teacher.getPosition());
                t.setSchoolToAttend(teacher.getSchoolToAttend());
                t.setStatus(1);
                t.setUpdateTime(new Date());
                t.setDeleted(0);
                List<Subject> sub = subjectRepository.findByIdIn(subId);
                t.setSubject(sub);
                if(sub.isEmpty()){
                    throw new Exception("Không được để trống!");
                }
                List<Class> cls = classRepository.findByIdIn(classId);
                t.setAClass(cls);
                if(cls.isEmpty()){
                    throw new Exception("Không được để trống!");
                }
                teacherRepository.save(t);
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
            Boolean kt = teacherRepository.existsByIdIn(id);
            if(kt){
                teacherRepository.delete(id);
            }
           else {
               throw new Exception("Không tồn tại id!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Page<Teacher> search(String code, String name, String position, Pageable pageable) {
        try {
           return teacherRepository.search(code,name,position,pageable);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    public boolean validate(Teacher teacher){
        try{
            if(teacher == null){
                throw new Exception("Không có dữ liệu!");
            }
            if(teacher.getId() == null){
                throw new Exception("Không tồn tại Id");
            }
            if(teacherRepository.existsByCode(teacher.getCode())){
                throw new Exception("Mã giảng viên đã tồn tại!");
            }
            if(teacher.getCode().isEmpty() || teacher.getCode() == null){
                throw new Exception("Không được để trống mã giảng viên!");
            }
            if(teacher.getName().isEmpty() || teacher.getName() == null){
                throw new RuntimeException("Không được để trống tên giảng viên!");
            }
            if(teacher.getAddress().isEmpty() || teacher.getAddress() == null){
                throw new Exception("Không được để trống địa chỉ!");
            }
            if(teacher.getPosition().isEmpty() || teacher.getPosition() == null){
                throw new Exception("Không được để trống chức vụ!");
            }
            if(teacher.getSchoolToAttend().isEmpty() || teacher.getSchoolToAttend() == null){
                throw new Exception("Không được để trống tên trường!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
