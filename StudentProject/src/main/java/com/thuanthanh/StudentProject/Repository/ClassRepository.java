package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Entity.Department;
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

@Repository
public interface ClassRepository extends JpaRepository<Class,Integer> {
    Boolean existsByCode(String code);
    Boolean existsByName(String name);
    @Modifying
    @Transactional
    @Query(value = "update class set status = 0, deleted =1 where id in (:id)",nativeQuery = true)
    void delete(@Param("id") List<Integer> id);
    Boolean existsByDepartment(Department department);
    Class findByIdAndStatusAndDeleted(Integer id,Integer status,Integer deleted);
    List<Class> findByIdIn(List<Integer> classId);
    @Query(value = "select * from class c where c.status =1 and c.deleted =0 and (:code is null or c.code like :code)",nativeQuery = true)
    Page<Class> search(@Param("code") String code, Pageable pageable);

    @Query(value = "select count(c.id) as 'Số lượng lớp' from class c where c.status = 1 and c.deleted =0",nativeQuery = true)
    Map<String,Object> quatityClass();
    Boolean existsByIdIn(List<Integer> id);
}
