package com.ttn.linkSharing.entities;

import javax.persistence.Entity;

@Entity
public class LinkResource extends Resource {

    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}