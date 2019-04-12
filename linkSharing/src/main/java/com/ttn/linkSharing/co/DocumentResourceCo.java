package com.ttn.linkSharing.co;

import org.springframework.web.multipart.MultipartFile;

public class DocumentResourceCo {

    private MultipartFile path;

    private String description;

    //topic(id) for document
    private Long id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getPath() {
        return path;
    }

    public void setPath(MultipartFile path) {
        this.path = path;
    }
}