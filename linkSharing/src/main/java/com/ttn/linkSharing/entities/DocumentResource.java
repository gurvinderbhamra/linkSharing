package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.co.DocumentResourceCo;

import javax.persistence.Entity;

@Entity
public class DocumentResource extends Resource {

    private String path;

    public DocumentResource(){ }

    public DocumentResource(DocumentResourceCo documentResourceCo, Topic topic){
        this.path = documentResourceCo.getPath().getOriginalFilename();
        this.setDescription(documentResourceCo.getDescription());
        this.setTopic(topic);
        this.setId(documentResourceCo.getId());
    }

    public String getLink(){
        return null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}