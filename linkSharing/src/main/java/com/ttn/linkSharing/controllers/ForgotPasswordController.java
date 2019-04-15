package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.EmailService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @RequestMapping("/forgotPassword")
    public String forgotPassword(){
        return "forgotPassword";
    }

    @RequestMapping("/forgotPasswordButton")
    public String forgotPassword(@RequestParam("email") String email, Model model){
        User user = userService.getUserByEmail(email);
        if(user == null){
            model.addAttribute("`", "User does not exist, please enter valid username.");
            return "redirect:/forgotPassword";
        }
        else{
            new Thread(() -> {
                try {
                    emailService.sendPasswormEmail(user.getEmail(), user.getPassword());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            model.addAttribute("message", "An email send to your email, please login with credentials and reset password");
            return "redirect:/";
        }
    }
}
