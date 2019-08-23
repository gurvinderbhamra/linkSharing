package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SignUpService {

    @Autowired
    UserRepository userRepository;

    private static final String UPLOAD_FILE = "/home/ttn/LinkSharing/linkSharing/out/production/resources/static/images/";

    public User createUser(@Valid @ModelAttribute User user, MultipartFile file) throws IOException {
        if(checkUser(user)){
            return null;
        }
        if (file.isEmpty()) {
            user.setPhoto(user.getUsername() + "_" + "user.png");
        }
        user.setActive(true);
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FILE + user.getUsername() + "_" + file.getOriginalFilename());
            Files.write(path, bytes);
            user.setPhoto(user.getUsername() + "_" + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        return user;
    }

    public Boolean checkUser(User user1){
        return userRepository.existsByUsernameOrEmail(user1.getUsername(), user1.getEmail());
    }
}
