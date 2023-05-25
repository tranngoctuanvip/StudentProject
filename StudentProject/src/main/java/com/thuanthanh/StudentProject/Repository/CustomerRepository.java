package com.thuanthanh.StudentProject.Repository;

import com.thuanthanh.StudentProject.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
    @Query(value = "select * from customers c",nativeQuery = true)
    List<Customer> getall();
    Boolean existsByEmail(String email);
    @Query(value = "select c.email from customers c", nativeQuery = true)
    List<Map<String,Customer>> getallEmail();
}
