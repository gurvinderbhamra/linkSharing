package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.enums.Seriousness;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    @Enumerated(EnumType.STRING)
    private Seriousness seriousness;

    public UserSubscription(Topic topic){
        this.topic = topic;
    }

    public UserSubscription(){ }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserSubscription that = (UserSubscription) o;

        if(o instanceof UserSubscription)
        {
            UserSubscription userSubscription = (UserSubscription) o;
            return userSubscription.topic.getTopicName() == this.topic.getTopicName();
        }
        return false;

//        Topic t = (Topic) o;
//        return Objects.equals(topic.getTopicName(), t.getTopicName());

//        return Objects.equals(id, that.id) &&
//                Objects.equals(user, that.user) &&
//                Objects.equals(topic, that.topic) &&
//                seriousness == that.seriousness;
    }
}