package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Constant.Constant;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {
    public static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ClassRepository classRepository;
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private String prefix = "GV00";
    @Override
    public void add(Teacher teacher, List<Integer> classId, List<Integer> subId) {
        try {
            if(!validate(teacher,classId,subId)){
                Teacher t = new Teacher();
                Path staticPath = Paths.get("static");
                Path imagePath = Paths.get("images");
                if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                    Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
                }
                t.setId(teacher.getId());
                teacherRepository.save(t);
                t.setCode(prefix+t.getId());
                Path file = CURRENT_FOLDER.resolve(staticPath)
                        .resolve(imagePath).resolve((Path) t.getImage());
                try (OutputStream os = Files.newOutputStream(file)) {
                    os.write(t.getImage().getBytes());
                }
                t.setImage((MultipartFile) imagePath.resolve((Path) t.getImage()));
                t.setName(teacher.getName());
                t.setAddress(teacher.getAddress());
                t.setBirthDay(teacher.getBirthDay());
                t.setPosition(teacher.getPosition());
                t.setSchoolToAttend(teacher.getSchoolToAttend());
                t.setStatus(Constant.ACTIVE_STATUS);
                t.setDeleted(Constant.INACTIVE_STATUS);
                t.setCreatTime(new Date());
                List<Subject> sub = subjectRepository.findByIdIn(subId);
                t.setSubject(sub);
                List<Class> cls = classRepository.findByIdIn(classId);
                t.setAClass(cls);
                teacherRepository.save(t);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Teacher update(Teacher teacher, Integer id, List<Integer> classId, List<Integer> subId) {
        try {
            if(!validate(teacher,classId,subId)){
                Teacher t = teacherRepository.findById(id).get();
                t.setName(teacher.getName());
                t.setAddress(teacher.getAddress());
                t.setBirthDay(teacher.getBirthDay());
                t.setPosition(teacher.getPosition());
                t.setSchoolToAttend(teacher.getSchoolToAttend());
                t.setUpdateTime(new Date());
                List<Subject> sub = subjectRepository.findByIdIn(subId);
                t.setSubject(sub);
                List<Class> cls = classRepository.findByIdIn(classId);
                t.setAClass(cls);
                teacherRepository.save(t);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
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
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Page<Teacher> search(String code, String name, String position, Pageable pageable) {
        try {
           Page<Teacher> page = teacherRepository.search(code,name,position,pageable);
           if(page.isEmpty()){
               throw new Exception("Không có dữ liệu!");
           }
           return page;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    public boolean validate(Teacher teacher, List<Integer> subId, List<Integer> classId) throws Exception {
        if(teacher == null){
            throw new Exception("Không có dữ liệu!");
        }
        if(classRepository.findByIdIn(classId).isEmpty()){
            throw new Exception("Lớp học đã có người dạy!");
        }
        if(subjectRepository.findByIdIn(subId).isEmpty()){
            throw new Exception("Môn học đã có người dạy!");
        }
        if(teacher.getName().isEmpty() || teacher.getName() == null){
            throw new Exception("Không được để trống tên giảng viên!");
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
        return false;
    }
}
