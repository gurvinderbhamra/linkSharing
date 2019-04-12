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
import javax.validation.Valid;

@Controller
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/topic/create", method = RequestMethod.POST)
    public String createTopic(@Valid @ModelAttribute("topic") Topic topic, HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);

        if(session != null) {
            if (session.getAttribute("userid") != null) {
                Long userId = (Long) session.getAttribute("userid");
                User user = userService.getUserById((Long) session.getAttribute("userid"));
                addAttributes(model, user);
                System.out.println(topic.getVisibility());
                topicService.createTopic(topic, userId);
            }
            else {
                return "redirect:/dashboard";
            }
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/topic/view/{topicId}")
    public String show(@PathVariable("topicId") Long topicId, HttpSession session, Model model){
        if(session != null){
            if(session.getAttribute("userid") != null){
                User user = userService.getUserById((Long) session.getAttribute("userid"));
                model.addAttribute("user", user);
                model.addAttribute("topic", topicService.getTopicByTopicId(topicId));
                model.addAttribute("linkResourceCo",new LinkResourceCo());
                model.addAttribute("documentResourceCo",new DocumentResourceCo());
                model.addAttribute("userTopics", topicService.countTopicsOfUser(user.getUsername()));
                return "topic";
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/topic/search")
    public String search(HttpSession session, Model model){
        if(session != null){
            if(session.getAttribute("userid") != null){
                User user = userService.getUserById((Long) session.getAttribute("userid"));
                addAttributes(model, user);
                return "search";
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
