package com.ttn.linkSharing.service;

import com.ttn.linkSharing.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    public String resource(){
        return "resource";
    }
}
