package com.ttn.linkSharing.controller;

import com.ttn.linkSharing.entity.User;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView indexPage(User user, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        if((Boolean) session.getAttribute("login")!= null && (Boolean) session.getAttribute("login") ) {
            modelAndView.addObject("user", userService.getUserById((Long)session.getAttribute("userid")));
            modelAndView.setViewName("dashboard");
        }
        else{
            modelAndView.addObject("user", user);
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @RequestMapping("/forgotPassword")
    @ResponseBody
    public String forgotPassword(){
        return "Forgot Password Processing (password reset kro bhai)";
    }
}
