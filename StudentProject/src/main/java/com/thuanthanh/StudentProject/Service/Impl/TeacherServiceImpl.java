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
import org.springframework.stereotype.Service;

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
    public void add(Teacher teacher,List<Integer> classId, List<Integer> subId) {
        try {
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
            List<Class> cls = classRepository.findByIdIn(classId);
            t.setAClass(cls);
            teacherRepository.save(t);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Teacher update(Teacher teacher, Integer id) {
        return null;
    }

    @Override
    public void delete(List<Integer> id) {

    }
}
