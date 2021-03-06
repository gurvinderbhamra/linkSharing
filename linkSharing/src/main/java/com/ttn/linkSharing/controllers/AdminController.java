package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.co.LoginCo;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping("/admin")
    public String adminLogin(HttpSession session, Model model) {
        model.addAttribute("user", new LoginCo());
        if (session != null) {
            if (session.getAttribute("admin") != null) {
                addAttributes(model, userService.getUserByEmail((String) session.getAttribute("admin")));
                return "users";
            }
        }
        return "admin";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@Valid @ModelAttribute("user") LoginCo loginCo, Model model, HttpSession session) {
        String username = loginCo.getUsername();
        String password = loginCo.getPassword();
        if (username.equals("admin") && password.equals("aaa")) {
            session.setAttribute("admin", username);
            addAttributes(model, userService.getUserByEmail((String) session.getAttribute("admin")));
            return "users";
        }
        model.addAttribute("user", new LoginCo());
        return "admin";
    }

    @GetMapping("/adminLogin")
    public String adminLogin(Model model, HttpSession session) {
        if(session != null){
            if(session.getAttribute("admin") != null){
                addAttributes(model, userService.getUserByEmail((String) session.getAttribute("admin")));
                return "users";
            }
        }
        return "redirect:/admin";
    }

    @GetMapping("/activation/{id}")
    public String activationOfUser(@PathVariable("id") Long id, Model model, HttpSession session){
        if(session != null){
            if(session.getAttribute("admin") != null){
                User user = userService.getUserById(id);
                System.out.println(user.getActive());
                if(user.getActive())
                    user.setActive(false);
                else if(!user.getActive())
                    user.setActive(true);
                addAttributes(model, user);
                userService.updateActivation(user);
                return "redirect:/adminLogin";
            }
        }
        return "redirect:/admin";
    }

    private void addAttributes(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("topic",new Topic());
        model.addAttribute("linkResourceCo",new LinkResourceCo());
        model.addAttribute("documentResourceCo", new DocumentResourceCo());
        model.addAttribute("userList", userService.getAllUsers());
    }
}
