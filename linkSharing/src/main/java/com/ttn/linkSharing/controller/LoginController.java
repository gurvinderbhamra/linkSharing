package com.ttn.linkSharing.controller;

import com.ttn.linkSharing.entity.User;
import com.ttn.linkSharing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/dashboard")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model){
        /*if(result.hasErrors()){
            model.addAttribute("loginErrorsList", result.getFieldErrors());
        }*/
        User user1 = loginService.login(user.getUsername(), user.getPassword());
        if(user1 == null){
            return "redirect:/";
        }
        else {
            session.setAttribute("login",true);
            session.setAttribute("userid", user.getUserId());
            model.addAttribute("username", user.getFirstName());
            return "dashboard";
        }
    }

    @GetMapping("/dashboard")
    public ModelAndView run(){
        return new ModelAndView("dashboard");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
}
