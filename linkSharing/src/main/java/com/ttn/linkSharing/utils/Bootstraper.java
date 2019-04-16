package com.ttn.linkSharing.utils;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.entities.UserSubscription;
import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.enums.Seriousness;
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
        user.setEmail("bhamra.gurvindersingh@gmail.com");
        user.setUsername("gurvinder");
        user.setPassword("123456");
        user.setRole(Role.USER);
        user.setPhoto("user.png");
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Gurvinder");
        user1.setLastName("Singh");
        user1.setEmail("gurvinder.singh@tothenew.com");
        user1.setUsername("admin");
        user1.setPassword("aaa");
        user1.setRole(Role.ADMIN);
        user1.setPhoto("user.png");
        userRepository.save(user1);

        for(int i = 1 ; i <= 5 ; i++){
            User user2 = new User();
            user2.setFirstName("Gurvinder" + i);
            user2.setLastName("Singh" + i);
            user2.setEmail("abc@abc.com" + i);
            user2.setUsername("user" + i);
            user2.setPassword("aaa");
            user2.setRole(Role.USER);
            user2.setPhoto("user.png");
            userRepository.save(user2);

        }

        Topic topic1 = new Topic();
        topic1.setTopicName("Java");
        topic1.setVisibility(Visibility.PUBLIC);
        UserSubscription subscription1 = new UserSubscription();
        subscription1.setUser(user);
        subscription1.setTopic(topic1);
        subscription1.setSeriousness(Seriousness.SERIOUS);
        topic1.setUserSubscriptions(Arrays.asList(subscription1));
        topic1.setCreatedBy(user.getUsername());
        topicRepository.save(topic1);
    }
}
