package com.ttn.linkSharing.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LogoutService {

    public String logout(HttpSession session){
        if(session != null){
            if((Boolean) session.getAttribute("login"))
            session.invalidate();
            return "redirect:/";
        }
        return "dashboard";
    }
}
