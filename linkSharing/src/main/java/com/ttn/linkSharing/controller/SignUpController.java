package com.ttn.linkSharing.controller;

import com.ttn.linkSharing.entity.User;
import com.ttn.linkSharing.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @PostMapping("/registerUser")
    ModelAndView signUp(@Valid @ModelAttribute("user") User user, BindingResult result){
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.addObject("signUpError", result.getAllErrors());
            modelAndView.setViewName("index");
        }
        User user1 = signUpService.createUser(user);
        if(user1 == null){
            modelAndView.setViewName("errors");
            return modelAndView;
        }
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }
}
