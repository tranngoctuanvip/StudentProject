//package com.thuanthanh.StudentProject.Service.Impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//@RequiredArgsConstructor
//@Service
//public class EmailServiceImpl implements EmailService {
//    private final  JavaMailSender javaMailSender;
//    @Override
//    public void sendOTP(String to, String otp){
//        try{
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message,true);
//            helper.setTo(to);
//            helper.setSubject("Your OTP");
//            helper.setText("Your One-Time Password is: " +otp);
//            javaMailSender.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e.getMessage(),e);
//        } catch (MailException e) {
//            throw new RuntimeException(e.getMessage(),e);
//        }
//    }
//}
