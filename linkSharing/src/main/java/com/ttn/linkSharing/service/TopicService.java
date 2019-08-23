package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.entities.UserSubscription;
import com.ttn.linkSharing.enums.Seriousness;
import com.ttn.linkSharing.enums.Visibility;
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

    public Boolean changeSeriousness(Long userSubscriptionId, String choosedSeriousness){
        UserSubscription userSubscription = userSubscriptionRepository.findById(userSubscriptionId).get();

        String selectedSeriousness = Seriousness.valueOf(choosedSeriousness.toUpperCase()).toString();

        Seriousness  seriousness = Seriousness.valueOf(selectedSeriousness.toUpperCase());

        if(selectedSeriousness.isEmpty()){
            return false;
        }
        else{
            userSubscription.setSeriousness(seriousness);
            userSubscriptionRepository.save(userSubscription);
            return  true;
        }
    }

    public Topic getTrendingTopic(){
        return topicRepository.getTrendingTopic().get();
    }

    public Boolean changeVisibility(Topic topic, String choosedVisibility){
        Visibility visibility = Visibility.valueOf(choosedVisibility.toUpperCase());
        if(choosedVisibility.isEmpty())return false;
        else{
            topic.setVisibility(visibility);
            topicRepository.save(topic);
            return true;
        }
    }

    public boolean subscribeTopic(User user, Topic topic){
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setSeriousness(Seriousness.CASUAL);
        userSubscription.setTopic(topic);
        userSubscription.setUser(user);
        boolean flag = false;
        if(!user.getUserSubscriptions().contains(topic)) {
            flag = user.getUserSubscriptions().add(userSubscription);
        }
        userSubscriptionRepository.save(userSubscription);
        for(UserSubscription userSubscription1 : user.getUserSubscriptions()){
            System.out.println(userSubscription1.getTopic().getTopicName());
        }
        if(flag)
            return true;
        return false;
    }

    public List<Topic> searchTopics(String search){
        return topicRepository.findTopics(search);
    }
}
