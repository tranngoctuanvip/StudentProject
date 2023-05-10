package com.thuanthanh.StudentProject.Repository.Impl;

import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Repository.studentServices;
import com.thuanthanh.StudentProject.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
@Service
public class StudentImpl implements studentServices {
    private static SessionFactory factory;
    @Override
    public List<Student> liststudent() {
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            List students = session.createCriteria(Student.class).list();
            return students;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Override
    public void updatestudent(StudentDto studentDto, Integer id) {

    }
}
