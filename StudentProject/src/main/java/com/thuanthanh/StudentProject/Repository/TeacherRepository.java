package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Teacher;
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
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update teacher set status = 0 ,deleted =1 where id in (:id)",nativeQuery = true)
    void delete(@Param("id")List<Integer> id);
    Boolean existsByCode(String code);
    @Query(value = "select * from teacher t where t.status = 1 and t.deleted = 0 and (:code is null or t.code like :code) \n" +
            "\tand (:name is null or t.name like :name) and (:position is null or t.`position` like :position)", nativeQuery = true)
    Page<Teacher> search(@Param("code") String code, @Param("name") String name, @Param("position") String position, Pageable pageable);
    @Query(value = "select count(t.id) as 'Số lượng giáo viên' from teacher t where t.status =1 and t.deleted =0",nativeQuery = true)
    Map<String,Object> quatityteacher();
    @Query(value = "select count(t.id) as 'giảng viên có bằng thạc sĩ' from teacher t where t.`position` = 'thạc sĩ' and t.status =1 and t.deleted =0",nativeQuery = true)
    Map<String,Object> quatityThs();
    @Query(value = "select count(t.id) as 'giảng viên có bằng tiến sĩ' from teacher t where t.`position` = 'tiến sĩ' and t.status = 1 and deleted = 0",nativeQuery = true)
    Map<String,Object> quatityTS();
}
