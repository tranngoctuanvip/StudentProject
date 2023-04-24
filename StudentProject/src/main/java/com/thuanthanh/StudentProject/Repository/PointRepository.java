package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update `point` set status = 1 , deleted = 0 where id  in (:id)",nativeQuery = true)
    void delete(@Param("id")List<Integer> id);

    Boolean existsByIdIn(List<Integer> id);
}
