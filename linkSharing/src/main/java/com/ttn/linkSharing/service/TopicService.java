package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.entities.UserSubscription;
import com.ttn.linkSharing.enums.Seriousness;
import com.ttn.linkSharing.repositories.TopicRepository;
import com.ttn.linkSharing.repositories.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserSubscriptionRepository userSubscriptionRepository;

    public Topic createTopic(Topic topic, Long userid){
        User user = userService.getUserById(userid);
        topic.setCreatedBy(user.getUsername());

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setSeriousness(Seriousness.CASUAL);
        userSubscription.setUser(user);
        userSubscription.setTopic(topic);

        topic.getUserSubscriptions().add(userSubscription);

        topicRepository.save(topic);
        return topic;
    }

    public Topic getTopicByTopicId(Long topicId){
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        return optionalTopic.get();
    }

    public Topic getTopicByName(String topicName){
        Optional<Topic> optionalTopic = topicRepository.findByTopicName(topicName);
        return optionalTopic.get();
    }

    public Integer countTopicsOfUser(String createdBy){
        return topicRepository.countByCreatedByLike(createdBy);
    }

    public Boolean deleteTopic(Long topicId){
        topicRepository.deleteById(topicId);
        return topicRepository.existsById(topicId);
    }

    public List<Topic> searchTopics(String search){
        return topicRepository.findTopics(search);
    }
}
