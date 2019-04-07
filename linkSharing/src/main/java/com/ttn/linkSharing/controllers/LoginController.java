package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.entities.User;
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
    public String login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, Model model){
        User user1 = loginService.login(user.getUsername(), user.getPassword());
        if(user1 != null){
            HttpSession session = request.getSession();
            session.setAttribute("login", true);
            session.setAttribute("userid", user1.getUserId());
            model.addAttribute("user", user1);
            return "dashboard";
        }
        else{
            return "index";
        }

        /*if(user1 == null){
            return "redirect:/";
        }
        else {
            session.setAttribute("login",true);
            session.setAttribute("userid", user.getUserId());
            System.out.println(user1);
            model.addAttribute("user", user1);
            return "dashboard";
        }*/
    }

    @GetMapping("/dashboard")
    public ModelAndView getMethodDashboard(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession(false);
        if(session != null) {
            if ((Boolean) session.getAttribute("login") != null) {
                modelAndView.setViewName("dashboard");
            }
        }
        else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }
}
