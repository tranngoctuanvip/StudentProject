package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Customer;
import com.thuanthanh.StudentProject.Repository.CustomerRepository;
import com.thuanthanh.StudentProject.Repository.StudentRepository;
import com.thuanthanh.StudentProject.Service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CustomerRepository customerRepository;
    private final JavaMailSender mailSender;
    private static final int EXPIRED_LENGTH = 6;
    @Override
    public void generateOneTimePassword(String email) throws MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        StringBuilder OTP = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        Customer customer = optionalCustomer.get();
        if(!optionalCustomer.isEmpty())
        {
//            String OTP = RandomString.make(10);
            for (int i = 0; i<EXPIRED_LENGTH;i++){
                OTP.append(secureRandom.nextInt(9));
            }
            customer.setOneTimePassword(OTP.toString());
            customer.setOtpRequestedTime(new Date());
            customerRepository.save(customer);
            sendOTPEmail(customer, OTP.toString());
        }
        else {
            throw new MessagingException("email không tồn tại!");
        }
    }

    @Override
    public void sendOTPEmail(Customer customer, String OTP) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("contact@shopme.com", "Shopme Support");
        helper.setTo(customer.getEmail());
        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
        String content = "<p>Hello " + customer.getEmail() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>"  + OTP +"</b></p>"
                + "<p><b> Click here: "+"http://localhost:8088/mail/changepassword?email=" + customer.getEmail() +"&pass="+ customer.getPassword() +"</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
    @Override
    public void clearOTP(Customer customer) {
        customer.setOneTimePassword(null);
        customer.setOtpRequestedTime(null);
        customerRepository.save(customer);
    }

    @Override
    public void changePassword(String email, String pass, Customer customer) throws MessagingException {
        Customer c = customerRepository.findByEmail(email).get();
            c.setOneTimePassword(customer.getOneTimePassword());
            if(customer.getOneTimePassword().equals(c.getOneTimePassword())){
                throw new MessagingException("Mã OTP chưa chính xác!");
            }
            if(pass.equals(c.getPassword())){
                c.setPassword(customer.getPassword());
        }
            else {
                throw new MessagingException("Mật khẩu không chính xác!");
            }
        customerRepository.save(c);
    }
}
