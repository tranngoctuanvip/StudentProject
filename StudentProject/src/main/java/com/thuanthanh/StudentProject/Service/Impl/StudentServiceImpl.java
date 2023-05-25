package com.thuanthanh.StudentProject.Service.Impl;


import com.thuanthanh.StudentProject.Constant.Constant;
import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Repository.ClassRepository;
import com.thuanthanh.StudentProject.Repository.StudentRepository;
import com.thuanthanh.StudentProject.Service.StudentService;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
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
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    public static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    private String prefix = "sv000";
    @Override
    public void add(Student student, Integer classid) {
        try{
            if(!validate(student)){
                Student sd = new Student();
                sd.setId(student.getId());
                studentRepository.save(sd);  // luu ID để gán xuống cho code
                sd.setCode(prefix + sd.getId()); // thêm code tự động
                sd.setName(student.getName());
                sd.setAddress(student.getAddress());
                sd.setBirthDay(student.getBirthDay());
                sd.setStatus(Constant.INACTIVE_STATUS);
                sd.setDeleted(Constant.ACTIVE_STATUS);
                sd.setSex(student.getSex());
                sd.setAClass(classRepository.findByIdAndStatusAndDeleted(classid,1,0));
                sd.setCreatTime(new Date());
                studentRepository.save(sd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Student update(Student student, Integer id, Integer classid) {
        try{
            if(!validate(student)){
                Student sd = studentRepository.findById(id).get();
                sd.setName(student.getName());
                sd.setAddress(student.getAddress());
                sd.setBirthDay(student.getBirthDay());
                sd.setStatus(Constant.ACTIVE_STATUS);
                sd.setDeleted(Constant.INACTIVE_STATUS);
                sd.setSex(student.getSex());
                sd.setAClass(classRepository.findByIdAndStatusAndDeleted(classid,Constant.ACTIVE_STATUS,Constant.INACTIVE_STATUS));
                sd.setUpdateTime(new Date());
                studentRepository.save(sd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return null;
    }
    @Override
    public void delete(List<Integer> id) {
        try {
            Boolean kt = studentRepository.existsByIdIn(id);
            if(kt){
                studentRepository.deleted(id);
            }
            else {
                throw new Exception("Không tồn tại Id!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public Page<Student> searchByCodeAndName(String code, String name, Integer sex, Pageable pageable) {
        try {
            Page<Student> searchbycodeandname = studentRepository.searchbycodeandname(code,name,sex,pageable);
            if(searchbycodeandname.isEmpty()){
                throw new Exception("Không có dữ liệu!");
            }
            return searchbycodeandname;
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public List<StudentDto> export() {
        try {
           List<Student> students = studentRepository.findAllByStatusAndDeleted(Constant.ACTIVE_STATUS,Constant.INACTIVE_STATUS);
           List<StudentDto> studentDtos = new ArrayList<>();
           for (Student student :students){
                studentDtos.add(new StudentDto(student));
           }
            return studentDtos;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    @Override
    public List<Student> list(String name) {
        return studentRepository.findByNameIsNotIgnoreCaseOrNameIsNotNull(name);
    }

    @Override
    public Boolean saveAll(List<Student> students) {
        try {
            studentRepository.saveAll(students);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean save(List<Integer> id) {
        try {
            Student student = new Student();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Student upload(String name, String address, String birthday, Integer sex, Integer clazz, MultipartFile image) {
        try {
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(Objects.requireNonNull(image.getOriginalFilename()));
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image.getBytes());
            }
            String req = "http://localhost:8088/" + imagePath.resolve(image.getOriginalFilename());
            Student st = new Student();
            st.setId(st.getId());
            studentRepository.save(st);
            st.setName(name);
            st.setCode(prefix+st.getId());
            st.setAddress(address);
            st.setBirthDay(birthday);
            st.setSex(sex);
            st.setAClass(classRepository.findById(clazz).get());
            st.setCreatTime(new Date());
            st.setStatus(Constant.ACTIVE_STATUS);
            st.setDeleted(Constant.INACTIVE_STATUS);
            st.setImage(req.replace('\\','/'));
            return studentRepository.save(st);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public void deleteMany(List<Integer> id) {
        try {
            Boolean check = studentRepository.existsByIdIn(id);
            if(check){
                studentRepository.deleted(id);
            }
            else
                throw new MessageDescriptorFormatException("Id không tồn tại!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private boolean validate(Student student) throws Exception {
        if(student == null){
            throw new Exception("Không có dữ liệu!");
        }
        if(student.getName().isEmpty() || student.getName() == null){
            throw new Exception("Tên sinh viên không được để trống!");
        }
        if(student.getAddress().isEmpty() || student.getAddress() == null){
            throw new Exception("Địa chỉ không được để trống!");
        }
        return false;
    }
}
