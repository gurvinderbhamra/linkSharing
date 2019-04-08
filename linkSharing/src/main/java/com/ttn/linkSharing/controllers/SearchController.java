package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.service.SearchService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    UserService userService;

    @RequestMapping("/search")
    public String search(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            model.addAttribute("user", userService.getUserById((Long)session.getAttribute("userid")));
        }
        return searchService.search("search text");
    }
}
