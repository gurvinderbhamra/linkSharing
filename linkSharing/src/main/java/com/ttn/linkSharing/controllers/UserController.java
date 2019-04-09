package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            modelAndView.addObject("topic", new Topic());
            modelAndView.setViewName("dashboard");
        }
        else{
            modelAndView.addObject("user", user);
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @RequestMapping("/forgotPassword")
    public String forgotPassword(){
        return "Forgot Password Processing (password reset kro bhai)";
    }

    @RequestMapping("/editProfile")
    public String editProfile(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session != null) {
            User user1 = userService.getUserById((Long) session.getAttribute("userid"));
            model.addAttribute("topic", new Topic());
            model.addAttribute("user", user1);
            if(user1 != null)
                return "editProfile";
        }
        return "redirect:/";
    }

    @RequestMapping("/userProfile")
    public String userProfile(HttpSession session, Model model){
        User user = userService.getUserById((Long) session.getAttribute("userid"));
        model.addAttribute("user", user);
        model.addAttribute("topic", new Topic());
        return "userProfile";
    }

}
