package com.ttn.linkSharing.utils;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.entities.UserSubscription;
import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.enums.Visibility;
import com.ttn.linkSharing.repositories.TopicRepository;
import com.ttn.linkSharing.repositories.UserRepository;
import com.ttn.linkSharing.service.SignUpService;
import com.ttn.linkSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstraper {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void bootstrapData(){
        User user = new User();
        user.setFirstName("Gurvinder");
        user.setLastName("Singh");
        user.setEmail("gurvinder.singh@tothenew.com");
        user.setUsername("admin");
        user.setPassword("123");
        user.setRole(Role.ADMIN);
        user.setPhoto("user.png");
        userRepository.save(user);

        Topic topic1 = new Topic();
        topic1.setTopicName("Java");
        topic1.setVisibility(Visibility.PUBLIC);
        UserSubscription subscription1 = new UserSubscription();
        subscription1.setUser(user);
        subscription1.setTopic(topic1);
        topic1.setUserSubscriptions(Arrays.asList(subscription1));
        topic1.setCreatedBy(user.getUsername());
        topicRepository.save(topic1);
    }
}
