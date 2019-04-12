package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.co.SignupCo;
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

import javax.servlet.http.HttpSession;
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
    ModelAndView signUp(@Valid @ModelAttribute("signupCo") SignupCo signupCo, BindingResult result, @RequestParam("photoPath") MultipartFile file, HttpSession session) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.addObject("signUpError", result.getAllErrors());
            modelAndView.setViewName("index");
        }
        User user = new User(signupCo);
        System.out.println(signupCo);
        User user1 = signUpService.createUser(user, file);
        if(user1 == null){
            modelAndView.setViewName("errors");
            return modelAndView;
        }

        new Thread(()->{
            try {
                emailService.sendEmail(user1.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        modelAndView.addObject("user", user1);
        modelAndView.addObject("topic", new Topic());
        modelAndView.addObject("linkResourceCo",new LinkResourceCo());
        modelAndView.addObject("documentResourceCo",new DocumentResourceCo());
        modelAndView.setViewName("dashboard");
        session.setAttribute("login", true);
        session.setAttribute("userid", user1.getId());
        return modelAndView;
    }
}
