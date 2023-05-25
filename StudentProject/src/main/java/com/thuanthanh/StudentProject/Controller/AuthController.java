package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.DTO.CustomerDTO;
import com.thuanthanh.StudentProject.Entity.Message;
import com.thuanthanh.StudentProject.Service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private CustomerService customerService;
    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody CustomerDTO customerDTO){
        try {
            if(customerService.existByEmail(customerDTO.getEmail())){
                return new ResponseEntity<>(new Message("Email đã tồn tại!"), HttpStatus.OK);
            }
            else{
                customerService.create(customerDTO);
                return new ResponseEntity<>(new Message("Đăng ký thành công!"),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody CustomerDTO customerDTO){
        try{
            return new ResponseEntity<>(customerService.signin(customerDTO),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
