package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.enums.Visibility;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;

    private Visibility visibility;

    @Column(unique = true)
    private String topicName;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //user that created topic
    private User topicOwner;

    //list of users that has subscribed this topic
//    @ManyToMany
//    private List<User> userList;

//    @OneToMany
//    private User_Topic user_topic;
}
