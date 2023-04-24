package com.thuanthanh.StudentProject.Repository;


import com.thuanthanh.StudentProject.Entity.DTO.StudentDto;
import com.thuanthanh.StudentProject.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Boolean existsByCode(String code);
    Boolean existsByIdIn(List<Integer> id);
    @Query(value = "select * from student c where c.deleted = 0 and c.status = 1 and c.id = :id",nativeQuery = true)
    Student getall(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "update student set status = 0 , deleted = 1 where id in (:id)", nativeQuery = true)
    void deleted(@Param("id") List<Integer> id);
    @Query(value = "\tselect s.code , s.name ,p2.medium_score, s.birth_day from `point` p2 join student s on s.id = p2.student_id \n" +
            " \twhere p2.status =1 and p2.deleted =0 and s.deleted =0 and s.status =1 order by p2.medium_score desc limit 5",nativeQuery = true)
    List<Map<String,Object>> top5point();
    @Query(value = "select count(s.id) as 'Số lượng sinh viên' from student s where s.status =1 and s.deleted = 0", nativeQuery = true)
    Map<String,Object> sv();
    @Query(value = "select count(s.id) as 'Số lượng sinh viên nam' from student s where s.sex =0 and s.status =1 and s.deleted =0", nativeQuery = true)
    Map<String,Object> svboy();
    @Query(value = "select count(s.id) as 'Số lượng sinh viên nữ' from student s where s.sex =1 and s.status =1 and s.deleted =0",nativeQuery = true)
    Map<String,Object> svgrid();
    @Query(value = "select count(s.id) as 'Số sinh viên có điểm dưới 4' from student s join `point` p on s.id =p.student_id where p.medium_score <=4",nativeQuery = true)
    Map<String,Object> top4under();
    @Query(value = "select * from student s where s.status = 1 and s.deleted =0 \n" +
            "\tand (:code is null or s.code like :code) and (:name is null or s.name like :name) and (:sex is null or s.sex like :sex)",nativeQuery = true)
    Page<Student> searchbycodeandname(@Param("code") String code, @Param("name") String name,@Param("sex") Integer sex, Pageable pageable);
    @Query(value = "select s.code, s.name, s.birth_day, s.address, s.sex, s.class_id from student s where s.status  =1 and s.deleted = 0 and s.sex = :sex",nativeQuery = true)
    Map<String,Object> searchbysex(@Param("sex") Integer sex);
}
