package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.co.PasswordCo;
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

    @RequestMapping("/editProfile")
    public String editProfile(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session != null) {
            User user1 = userService.getUserById((Long) session.getAttribute("userid"));
            addAttributes(model, user1);
            return "editProfile";
        }
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, HttpSession session, Model model){
        if(session != null){
            if(session.getAttribute("userid") != null){
                User existingUser = userService.getUserById((Long) session.getAttribute("userid"));

                if(userService.updateUser(existingUser, user) != null){
                    model.addAttribute("message", "User updated successfully");
                }
                else{
                    model.addAttribute("message", "User updation failed, please try again");
                }
                addAttributes(model, existingUser);
                return "editProfile";
            }
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

    @RequestMapping("/updatePassword")
    public String updatePassword(@ModelAttribute PasswordCo passwordCo, HttpSession session, Model model){
        if (session != null) {
            if (session.getAttribute("userid") != null) {
                User user1 = userService.getUserById((Long) session.getAttribute("userid"));
                addAttributes(model, user1);
                if(passwordCo.getPassword().equals(passwordCo.getConfirmPassword())) {
                    user1.setPassword(passwordCo.getPassword());
                    user1.setConfirmPassword(passwordCo.getConfirmPassword());
                    userService.updatePassword(user1);
                    model.addAttribute("passwordMessage", "Password updated successfully");
                    return "editProfile";
                }
                else{
                    model.addAttribute("passwordMessage", "Please enter same password in both fields");
                    return "editProfile";
                }
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
        model.addAttribute("passwordCo", new PasswordCo());
    }
}
