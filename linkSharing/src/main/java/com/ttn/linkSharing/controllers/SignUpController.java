package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.EmailService;
import com.ttn.linkSharing.service.LoginService;
import com.ttn.linkSharing.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @Autowired
    LoginService loginService;

    @Autowired
    EmailService emailService;

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
            emailService.sendEmail(user1.getEmail());
        } catch (Exception e) {
            System.out.println(e);
        }
        modelAndView.addObject("topic", new Topic());
        modelAndView.setViewName("dashboard");
        System.out.println(user1.getPhoto());
        return modelAndView;
    }
}
