package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    public Topic getTopic(Long topicId){
        return topicRepository.findByTopicId(topicId);
    }
}
