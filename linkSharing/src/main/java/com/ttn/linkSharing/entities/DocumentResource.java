package com.ttn.linkSharing.entities;

import javax.persistence.Entity;

@Entity
public class DocumentResource extends Resource {

    private String path;

    public DocumentResource(){ }

    public DocumentResource(DocumentResource documentResource, Topic topic){
        this.path = documentResource.getPath();
        this.setTopic(topic);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}