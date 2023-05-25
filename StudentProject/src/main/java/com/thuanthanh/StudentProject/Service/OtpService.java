package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Customer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
@Configuration
@EnableScheduling
@Component
public interface OtpService {
    void generateOneTimePassword(String email) throws MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException;
    void sendOTPEmail(Customer customer, String OTP) throws MessagingException, UnsupportedEncodingException;
    void clearOTP(Customer customer);
    void changePassword(String email,String pass, Customer customer) throws MessagingException;
}
