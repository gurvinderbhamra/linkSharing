package com.ttn.linkSharing.controller;

import com.ttn.linkSharing.entity.User;
import com.ttn.linkSharing.service.LoginService;
import com.ttn.linkSharing.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    LoginService loginService;

    @PostMapping("/registerUser")
    ModelAndView signUp(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("photoPath") MultipartFile file) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.addObject("signUpError", result.getAllErrors());
            modelAndView.setViewName("index");
        }
        User user1 = signUpService.createUser(user, file);
        if(user1 == null){
            modelAndView.setViewName("errors");
            return modelAndView;
        }
        try {
            sendEmail(user1.getEmail());
        } catch (Exception e) {
            System.out.println(e);
        }
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    private void sendEmail(String email) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("This is your confirmation email");
        helper.setSubject("Link Sharing Confirmation");
        sender.send(message);
    }
}
