package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.enums.Visibility;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Visibility visibility;

    @Column(unique = true)
    @NotNull(message = "Please enter topic name")
    @Size(min = 1, message = "Topic name must contain at least 1 character")
    private String topicName;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @CreatedBy
    private String createdBy;

    @OneToMany(mappedBy = "topic", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserSubscription> userSubscriptions =new ArrayList<>();

    @OneToMany(mappedBy = "topic", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Resource> resources =new ArrayList<>();

    public Topic(){ }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<UserSubscription> getUserSubscriptions() {
        return userSubscriptions;
    }

    public void setUserSubscriptions(List<UserSubscription> userSubscriptions) {
        this.userSubscriptions = userSubscriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}