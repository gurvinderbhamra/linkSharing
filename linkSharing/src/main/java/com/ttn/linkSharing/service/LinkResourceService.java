package com.ttn.linkSharing.service;

import com.ttn.linkSharing.co.LinkResourceCo;
import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.entities.UserResource;
import com.ttn.linkSharing.repositories.LinkResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkResourceService {

    @Autowired
    LinkResourceRepository linkResourceRepository;

    @Autowired
    TopicService topicService;

    @Autowired
    UserService userService;

    public LinkResource getLinkResource(Long id){
        Optional<LinkResource> optional = linkResourceRepository.findById(id);
        return optional.get();
    }

    public LinkResource createLinkResource(LinkResourceCo linkResourceCo, Long userid){
        User user = userService.getUserById(userid);

        LinkResource linkResource = new LinkResource(linkResourceCo, topicService.getTopicByTopicId(linkResourceCo.getId()));
        Topic topic = topicService.getTopicByName(linkResource.getTopic().getTopicName());

        UserResource userResource = new UserResource();
        userResource.setResource(linkResource);
        userResource.setUser(user);
        userResource.setRating(0.0);
        userResource.setRead(false);

        linkResource.setTopic(topic);
        linkResource.getUserResources().add(userResource);

        return linkResourceRepository.save(linkResource);
    }
}
