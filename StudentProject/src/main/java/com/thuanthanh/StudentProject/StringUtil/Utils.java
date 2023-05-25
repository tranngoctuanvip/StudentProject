package com.thuanthanh.StudentProject.StringUtil;


import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;


public class Utils {
    public String convertbirthday(String dateFormat){
        return dateFormat;
    }
    public String isValidEmail(String email){
        String email_regex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        boolean kt = email.matches(email_regex);
        if(kt==false){
            throw new MessageDescriptorFormatException("Email không đúng định dạng!");
        }
        return email;
    }
    public String checkPhone(String phone) {
        // Bieu thuc chinh quy mo ta dinh dang so dien thoai
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        // Kiem tra dinh dang
        boolean kt = phone.matches(reg);
        if (kt == false) {
            throw new MessageDescriptorFormatException("SĐT không đúng định dạng!");
        }
        return phone;
    }
}
