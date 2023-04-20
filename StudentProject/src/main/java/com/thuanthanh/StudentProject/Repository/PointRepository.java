package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point,Integer> {
}
