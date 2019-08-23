package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.co.LoginCo;
import com.ttn.linkSharing.co.TopicCo;
import com.ttn.linkSharing.dto.TrendingTopicsDTO;
import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.LoginService;
import com.ttn.linkSharing.service.TopicService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @PostMapping("/dashboard")
    public String login(@Valid @ModelAttribute("loginCo")LoginCo loginCo, BindingResult result, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
        User user1 = loginService.login(loginCo.getUsername(), loginCo.getPassword());
        if(user1 != null && user1.getActive()){
            HttpSession session = request.getSession();
            Topic trendingTopic = topicService.getTrendingTopic();

            session.setAttribute("login", true);
            session.setAttribute("userid", user1.getId());

            model.addAttribute("user", user1);
            model.addAttribute("trendingTopic", trendingTopic);
            model.addAttribute("topic",new Topic());
            model.addAttribute("linkResourceCo", new LinkResourceCo());
            model.addAttribute("documentResourceCo", new DocumentResourceCo());

            return "dashboard";
        }
        redirectAttributes.addFlashAttribute("message", "Invalid Username or Password");
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String getMethodDashboard(Model model, HttpSession session){
        if(session != null) {
            if ((Boolean) session.getAttribute("login") != null) {

                User user = userService.getUserById((Long) session.getAttribute("userid"));
                Topic trendingTopic = topicService.getTrendingTopic();
                model.addAttribute("trendingTopic", trendingTopic);
                model.addAttribute("user", user);
                model.addAttribute("topic",new Topic());
                model.addAttribute("linkResourceCo",new LinkResourceCo());
                model.addAttribute("documentResourceCo", new DocumentResourceCo());
                return "dashboard";
            }
        }
        return "redirect:/";
    }
}
