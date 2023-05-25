package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Customer;
import com.thuanthanh.StudentProject.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("email")
public class OTPController {
   @Autowired
    private OtpService otpService;
   @PostMapping("send-otp")
    public ResponseEntity<?> sendOTP(@RequestParam String email){
       try {
           otpService.generateOneTimePassword(email);
           return ResponseEntity.ok("Đã gửi mã OTP vui long kiểm tra mail!");
       } catch (Exception e) {;
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   @PostMapping("changepassword")
    public ResponseEntity<?> changepassword(@RequestParam String email,@Param("pass") String pass, @RequestBody Customer customer){
       try{
           otpService.changePassword(email, pass, customer);
           return ResponseEntity.ok("Đổi mật khẩu thành công!");
       } catch (MessagingException e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
}
