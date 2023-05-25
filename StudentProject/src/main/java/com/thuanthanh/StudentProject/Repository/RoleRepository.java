package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRole(String role);
}
