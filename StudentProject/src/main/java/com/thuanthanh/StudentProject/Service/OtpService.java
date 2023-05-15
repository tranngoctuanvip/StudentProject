package com.thuanthanh.StudentProject.Service;

import com.thuanthanh.StudentProject.Entity.Customer;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface OtpService {
    void generateOneTimePassword(String email) throws MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException;
    void sendOTPEmail(Customer customer, String OTP) throws MessagingException, UnsupportedEncodingException;
    void clearOTP(Customer customer);
    void changePassword(String email,String pass, Customer customer) throws MessagingException;
}
