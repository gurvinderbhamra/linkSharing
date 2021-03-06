package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.dto.ResourceDTO;
import com.ttn.linkSharing.entities.*;
import com.ttn.linkSharing.service.DocumentResourceService;
import com.ttn.linkSharing.service.LinkResourceService;
import com.ttn.linkSharing.service.ResourceService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ResourceController {

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    LinkResourceService linkResourceService;

    @Autowired
    DocumentResourceService documentResourceService;

    @RequestMapping("/resource/create/link")
    public String createLinkResource(@Valid @ModelAttribute("linkResourceCo") LinkResourceCo linkResourceCo,
                                     HttpSession session,
                                     Model model){
        if(session != null){
            if(session.getAttribute("userid") != null){
                Long userid = (Long) session.getAttribute("userid");
                User user = userService.getUserById(userid);
                addAttributes(model, user);
                if(linkResourceService.createLinkResource(linkResourceCo, userid) != null){
                    return "redirect:/dashboard";
                }
            }
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/resource/create/document")
    public String createDocumentResource(DocumentResourceCo documentResourceCo,
                                         HttpSession session,
                                         Model model,
                                         @RequestParam("path") MultipartFile file){
        if(session != null){
            if(session.getAttribute("userid") != null){
                Long userid = (Long) session.getAttribute("userid");
                User user = userService.getUserById(userid);
                addAttributes(model, user);
                documentResourceService.createDocumentResource(documentResourceCo, file, userid);
                return "redirect:/dashboard";
            }
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/resource/view/{id}")   //@PathVariable String id, {id}
    public String viewResource(@PathVariable("id") Long id, HttpSession session, Model model){
        if(session != null){
            if(session.getAttribute("userid") != null){
                Long userid = (Long) session.getAttribute("userid");
                User user = userService.getUserById(userid);
                addAttributes(model, user);
                model.addAttribute("resource", resourceService.getResourceById(id));
                return "resource";
            }
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/editResource/{id}")
    public String editResource(@PathVariable("id") Long id, @RequestParam("description") String description, HttpSession session, Model model){
        if(session != null){
            if(session.getAttribute("userid") != null){
                User user = userService.getUserById((Long) session.getAttribute("userid"));
                Resource resource = resourceService.getResourceById(id);
                resource.setDescription(description);
                resourceService.updateResource(resource);
                addAttributes(model, user);
                return "redirect:/resource/view/" + id;
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/delete/resource/{id}")
    public String deleteResource(@PathVariable("id") Long id, HttpSession session, Model model) {
        if (session != null) {
            if (session.getAttribute("userid") != null) {
                User user = userService.getUserById((Long) session.getAttribute("userid"));
                Resource resource = resourceService.getResourceById(id);
                resourceService.delete(resource);
                addAttributes(model, user);
                return "redirect:/dashboard";
            }
        }
        return "redirect:/";
    }

    private Model addAttributes(Model model, User user){
        model.addAttribute("user", user);
        model.addAttribute("topic", new Topic());
        model.addAttribute("linkResourceCo", new LinkResourceCo());
        model.addAttribute("documentResourceCo", new DocumentResourceCo());
        return model;
    }
}
