package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Customer;
import com.thuanthanh.StudentProject.Entity.DTO.CustomerDTO;

public interface CustomerService {
    Customer create(CustomerDTO customerDTO);
    Boolean existByEmail(String email);
    CustomerDTO signin(CustomerDTO customerDTO);
}
