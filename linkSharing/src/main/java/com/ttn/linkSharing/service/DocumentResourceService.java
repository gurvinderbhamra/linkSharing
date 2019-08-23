package com.ttn.linkSharing.service;

import com.ttn.linkSharing.co.DocumentResourceCo;
import com.ttn.linkSharing.entities.DocumentResource;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.entities.UserResource;
import com.ttn.linkSharing.repositories.DocumentResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentResourceService {

    @Autowired
    DocumentResourceRepository documentResourceRepository;

    @Autowired
    TopicService topicService;

    @Autowired
    UserService userService;

    private static final String UPLOAD_FILE = "/home/ttn/LinkSharing/linkSharing/src/main/resources/static/documents/";

    public DocumentResource createDocumentResource(DocumentResourceCo documentResourceCo, MultipartFile file, Long userid){
        User user = userService.getUserById(userid);

        DocumentResource documentResource = new DocumentResource(documentResourceCo, topicService.getTopicByTopicId(documentResourceCo.getId()));
        documentResource.setUser(user);
        Topic topic = topicService.getTopicByName(documentResource.getTopic().getTopicName());
        documentResource.setTopic(topic);

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FILE + documentResource.getId() + "_" + file.getOriginalFilename());
            Files.write(path, bytes);
            documentResource.setPath(documentResource.getId() + "_" + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserResource userResource = new UserResource();
        userResource.setRead(false);
        userResource.setRating(0.0);
        userResource.setUser(user);
        userResource.setResource(documentResource);

        documentResource.getUserResources().add(userResource);

        return documentResourceRepository.save(documentResource);
    }

    public List<DocumentResource> searchDocumentResource(String search){
        return documentResourceRepository.searchDocument(search);
    }
}
