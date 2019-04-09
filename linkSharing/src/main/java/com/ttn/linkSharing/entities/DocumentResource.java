package com.ttn.linkSharing.entities;

import javax.persistence.Entity;

@Entity
public class DocumentResource extends Resource {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}