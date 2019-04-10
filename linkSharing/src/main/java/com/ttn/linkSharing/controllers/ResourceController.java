package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.service.LinkResourceService;
import com.ttn.linkSharing.service.ResourceService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    LinkResourceService linkResourceService;

    @RequestMapping("/create/link")
    public String createLinkResource(@Valid @ModelAttribute("linkResourceCo") LinkResourceCo linkResourceCo,
                                     HttpSession session,
                                     Model model){
        if(session != null){
            if(session.getAttribute("userid") != null){
                Long userid = (Long) session.getAttribute("userid");
                User user = userService.getUserById(userid);

                model.addAttribute("user", user);
                model.addAttribute("topic", new Topic());
                model.addAttribute("linkResourceCo", new LinkResourceCo());
                if(linkResourceService.createLinkResource(linkResourceCo) != null){
                    return "redirect:/dashboard";
                }
            }
        }
        return "redirect:/dashboard";
    }
}
