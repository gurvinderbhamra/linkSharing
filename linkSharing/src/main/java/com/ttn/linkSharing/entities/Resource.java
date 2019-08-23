package com.ttn.linkSharing.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private Date resourceCreatedOn;

    @UpdateTimestamp
    private Date resourceUpdatedOn;

    @ManyToOne
    private Topic topic;

    //changes here
    @ManyToOne
    private User user;

    private String description;

    abstract String getLink();

    @OneToMany(mappedBy = "resource", orphanRemoval = true, cascade = CascadeType.ALL)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

}