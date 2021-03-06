package com.ttn.linkSharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(String email) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("This is your confirmation email");
        helper.setSubject("Link Sharing Confirmation");
        sender.send(message);
    }

    public void sendPasswormEmail(String email, String password) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("Hi, please login with the following credentials and change the password.\n\n Email : " + email + "\n Password : " + password);
        helper.setSubject("Link Sharing Forgot Password Mail");
        sender.send(message);
    }
}
