package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.ResourceService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResourceController {

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @RequestMapping("/resource")
    public String resource(HttpServletRequest request, Model model){
        User user = userService.getUserById((Long) request.getSession().getAttribute("userid"));
        model.addAttribute("user", user);
        return resourceService.resource();
    }
}
