package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.service.TopicService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTopic(@Valid @ModelAttribute("topic") Topic topic, HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
//        ModelAndView modelAndView = new ModelAndView("dashboard");
        if(session != null) {
            if (session.getAttribute("userid") != null) {
                Long userId = (Long) session.getAttribute("userid");

                model.addAttribute("user", userService.getUserById((Long) session.getAttribute("userid")));
                model.addAttribute("topic", topic);

                System.out.println(topic.getTopicName());
                topicService.createTopic(topic, userId);
            }
            else {
                return "redirect:/dashboard";
            }
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/show")
    public ModelAndView show(Model model){
        ModelAndView modelAndView = new ModelAndView("topicCreation");
        model.addAttribute("topic", new Topic());
        return modelAndView;
    }
}
