package com.ttn.linkSharing.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class UserResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Resource resource;

    private Boolean isRead;

    private Double rating;

    @CreationTimestamp
    private Date resourceCreatedOn;

    @UpdateTimestamp
    private Date resourceUpdatedOn;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

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
}