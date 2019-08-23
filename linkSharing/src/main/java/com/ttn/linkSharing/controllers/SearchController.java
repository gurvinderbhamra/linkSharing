package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.co.PasswordCo;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SearchController {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @Autowired
    LinkResourceService linkResourceService;

    @Autowired
    DocumentResourceService documentResourceService;

    @RequestMapping("/search")
    public String search(@RequestParam("searchText") String searchText, Model model, HttpServletRequest request){
        System.out.println(searchText);
        HttpSession session = request.getSession(false);
        if(session != null && !searchText.trim().equals("")){
            if(session.getAttribute("userid") != null) {
                User user = userService.getUserById((Long) session.getAttribute("userid"));
                addAttributes(model, user);
                model.addAttribute("userList", userService.searchUser(searchText));
                model.addAttribute("topicList", topicService.searchTopics(searchText));
                model.addAttribute("linkResourceList", linkResourceService.searchLinkResource(searchText));
                model.addAttribute("documentResourceList", documentResourceService.searchDocumentResource(searchText));
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
        Topic trendingTopic = topicService.getTrendingTopic();
        model.addAttribute("trendingTopic", trendingTopic);
//        model.addAttribute("userTopics", topicService.countTopicsOfUser(user.getUsername()));
//        model.addAttribute("passwordCo", new PasswordCo());
    }
}
