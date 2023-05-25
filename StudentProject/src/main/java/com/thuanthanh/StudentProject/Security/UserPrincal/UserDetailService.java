package com.thuanthanh.StudentProject.Security.UserPrincal;

import com.thuanthanh.StudentProject.Entity.Customer;
import com.thuanthanh.StudentProject.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Email not found!"));
        return UserPrinciple.build(customer);
    }
}
