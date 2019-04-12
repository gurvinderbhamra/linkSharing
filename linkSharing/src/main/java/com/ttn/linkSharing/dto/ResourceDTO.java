package com.ttn.linkSharing.dto;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.UserResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceDTO {

    private Long id;

    private Date resourceCreatedOn;

    private Date resourceUpdatedOn;

    private Topic topic;

    private List<UserResource> userResources = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getResourceCreatedOn() {
        return resourceCreatedOn;
    }

    public void setResourceCreatedOn(Date resourceCreatedOn) {
        this.resourceCreatedOn = resourceCreatedOn;
    }

    public Date getResourceUpdatedOn() {
        return resourceUpdatedOn;
    }

    public void setResourceUpdatedOn(Date resourceUpdatedOn) {
        this.resourceUpdatedOn = resourceUpdatedOn;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<UserResource> getUserResources() {
        return userResources;
    }

    public void setUserResources(List<UserResource> userResources) {
        this.userResources = userResources;
    }
}
