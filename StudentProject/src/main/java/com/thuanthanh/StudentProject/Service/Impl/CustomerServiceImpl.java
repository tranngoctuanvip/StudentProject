package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Customer;
import com.thuanthanh.StudentProject.Entity.DTO.CustomerDTO;
import com.thuanthanh.StudentProject.Entity.Role;
import com.thuanthanh.StudentProject.Repository.CustomerRepository;
import com.thuanthanh.StudentProject.Repository.RoleRepository;
import com.thuanthanh.StudentProject.Security.UserPrincal.UserPrinciple;
import com.thuanthanh.StudentProject.Security.jwt.JwtProvider;
import com.thuanthanh.StudentProject.Service.CustomerService;
import com.thuanthanh.StudentProject.StringUtil.Utils;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    private Utils utils = new Utils();

    @Override
    public Customer create(CustomerDTO customerDTO) {
        try {
            Customer c = new Customer();
            if(!validate(customerDTO)){
                c.setEmail(utils.isValidEmail(customerDTO.getEmail()));
                c.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
                Set<String> stringSet = customerDTO.getRoles();
                Set<Role> roleSet = new HashSet<>();
                stringSet.forEach(role->{
                    switch (role){
                        case "admin":
                            Role admin = roleRepository.findByRole("admin").orElseThrow(()->new RuntimeException("Role not found!"));
                            roleSet.add(admin);
                            break;
                        case "edithor":
                            Role edithor = roleRepository.findByRole("edithor").orElseThrow(()->new RuntimeException("Role not found!"));
                            roleSet.add(edithor);
                            break;
                        case "author":
                            Role author = roleRepository.findByRole("author").orElseThrow(()->new RuntimeException("Role not found!"));
                            roleSet.add(author);
                            break;
                        default:
                            Role user = roleRepository.findByRole("user").orElseThrow(()->new RuntimeException("Role not found!"));
                            roleSet.add(user);
                            break;
                    }
                });
                c.setRoleSet(roleSet);
               return customerRepository.save(c);
            }
            return c;
        } catch (MessageDescriptorFormatException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public Boolean existByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public CustomerDTO signin(CustomerDTO customerDTO) {
        try {
            CustomerDTO c = new CustomerDTO();
            if(!validate(customerDTO)){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(customerDTO.getEmail(),customerDTO.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtProvider.genarateToken(authentication);
                UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
                c.setToken(token);
                c.setEmail(userPrinciple.getUsername());
                c.setPassword(userPrinciple.getPassword());
                c.setType(customerDTO.getType());
                c.setRole(userPrinciple.getAuthorities());
            }
            return c;
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private boolean validate(CustomerDTO customerDTO){
        if(customerDTO.getEmail().isEmpty() || customerDTO.getEmail() == null){
            throw new MessageDescriptorFormatException("Email không được bỏ trống!");
        }
        if(customerDTO.getPassword().isEmpty() || customerDTO.getPassword() ==null){
            throw new MessageDescriptorFormatException("Password không được để trống");
        }
        return false;
    }
}
