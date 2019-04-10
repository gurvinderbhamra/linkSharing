package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.LoginCo;
import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.LoginService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @PostMapping("/dashboard")
    public String login(@Valid @ModelAttribute("loginCo")LoginCo loginCo, BindingResult result, HttpServletRequest request, Model model){
        User user1 = loginService.login(loginCo.getUsername(), loginCo.getPassword());
        if(user1 != null){
            HttpSession session = request.getSession();
            session.setAttribute("login", true);
            session.setAttribute("userid", user1.getId());
            model.addAttribute("user", user1);
            model.addAttribute("topic",new Topic());
            model.addAttribute("linkResource", new LinkResource());
            return "dashboard";
        }
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String getMethodDashboard(Model model, HttpSession session){
        if(session != null) {
            if ((Boolean) session.getAttribute("login") != null) {

                User user = userService.getUserById((Long) session.getAttribute("userid"));
                model.addAttribute("user", user);
                model.addAttribute("topic",new Topic());
                model.addAttribute("linkResource",new LinkResource());
                return "dashboard";
            }
        }
        return "redirect:/";
    }
}
