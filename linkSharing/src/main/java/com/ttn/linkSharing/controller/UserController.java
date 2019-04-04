package com.ttn.linkSharing.controller;

import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView indexPage(HttpSession session){
        if((Boolean) session.getAttribute("login")!= null && (Boolean) session.getAttribute("login") )
        {
            return new ModelAndView("dashboard");
        }
        return new ModelAndView("index");
    }

    @RequestMapping("/forgotPassword")
    @ResponseBody
    public String forgotPassword(){
        return "Forgot Password Processing (password reset kro bhai)";
    }
}
