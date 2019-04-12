package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.TopicService;
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

    @Autowired
    TopicService topicService;

    @RequestMapping("/")
    public ModelAndView indexPage(User user, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        if (session != null) {
            if (session.getAttribute("userid") != null) {
                User user1 = userService.getUserById((Long) session.getAttribute("userid"));
                modelAndView.addObject("user", user1);
                modelAndView.addObject("topic", new Topic());
                modelAndView.addObject("linkResourceCo", new LinkResourceCo());
                modelAndView.addObject("documentResourceCo", new DocumentResourceCo());
                modelAndView.addObject("userTopics", topicService.countTopicsOfUser(user1.getUsername()));
                modelAndView.setViewName("dashboard");
            }
        else{
                modelAndView.addObject("user", user);
                modelAndView.setViewName("index");
            }
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
            addAttributes(model, user1);
            if(user1 != null)
                return "editProfile";
        }
        return "redirect:/";
    }

    @RequestMapping("/userProfile/{id}")
    public String userProfile(@PathVariable Long id, HttpSession session, Model model){
        if(session != null) {
            if(session.getAttribute("userid") != null) {
                User user = userService.getUserById(id);
                addAttributes(model, user);
                return "userProfile";
            }
        }
        return "redirect:/";
    }

    private void addAttributes(Model model, User user){
        model.addAttribute("user", user);
        model.addAttribute("topic", new Topic());
        model.addAttribute("linkResourceCo",new LinkResourceCo());
        model.addAttribute("documentResourceCo",new DocumentResourceCo());
        model.addAttribute("userTopics", topicService.countTopicsOfUser(user.getUsername()));
    }
}
