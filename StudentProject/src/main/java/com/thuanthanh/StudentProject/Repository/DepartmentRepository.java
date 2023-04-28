package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update department set status = 0, deleted = 1 where id in (:id)",nativeQuery = true)
    void delete(@Param("id") List<Integer> id);
    Boolean existsByCode(String code);
    Boolean existsByName(String name);
    Boolean existsByIdIn(List<Integer> id);
    @Query(value = "select count(d.id) as 'Số lượng phòng học' from department d where d.deleted = 0 and d.status =1", nativeQuery = true)
    Map<String,Object> quatitydepart();
}
