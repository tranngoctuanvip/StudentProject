package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class,Integer> {
    Boolean existsByCode(String code);
    @Modifying
    @Transactional
    @Query(value = "update class set status = 0, deleted =1 where id in (:id)",nativeQuery = true)
    void delete(@Param("id")List<Integer> id);
    Boolean existsByDepartment(Department department);

    Class findByIdAndStatusAndDeleted(Integer id,Integer status,Integer deleted);

    List<Class> findByIdIn(List<Integer> classId);
}
