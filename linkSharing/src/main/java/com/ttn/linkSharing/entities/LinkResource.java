package com.ttn.linkSharing.entities;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class LinkResource extends Resource {

    @NotNull(message = "Please enter URL")
    @URL(message = "Invalid URL")
    private String link;

    private String description;

    public LinkResource(){ }

    public LinkResource(LinkResource linkResource, Topic topic){
        this.link = linkResource.getLink();
        this.description = linkResource.getDescription();
        this.setTopic(topic);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}