package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update subject set status = 0 , deleted =1 where id in (:id)", nativeQuery = true)
    void delete(@Param("id") List<Integer> id);

    @Query(value = "select * from subject s where s.deleted =0 and s.status =1 and s.id = :id",nativeQuery = true)
    Subject getall(@Param("id") Integer id);

    List<Subject> findByIdIn(List<Integer> subId);
}
