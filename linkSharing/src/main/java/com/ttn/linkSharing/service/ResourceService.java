package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.Resource;
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

    public Resource getResourceById(Long id){
        return resourceRepository.findById(id).get();
    }

    public void updateResource(Resource resource){
        resourceRepository.save(resource);
    }

    public void delete(Resource resource){
        resourceRepository.delete(resource);
    }
}
