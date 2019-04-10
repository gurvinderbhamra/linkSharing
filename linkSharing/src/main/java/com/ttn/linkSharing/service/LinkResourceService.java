package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Topic;
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

    public LinkResource getLinkResource(Long id){
        Optional<LinkResource> optional = linkResourceRepository.findById(id);
        return optional.get();
    }

    public LinkResource createLinkResource(LinkResource linkResource){

//        Topic topic = topicService.getTopicByName(linkResource.getTopic());
//        linkResource.setTopic(topic);

        return linkResourceRepository.save(linkResource);
    }
}
