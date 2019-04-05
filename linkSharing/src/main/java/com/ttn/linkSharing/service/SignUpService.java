package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entity.User;
import com.ttn.linkSharing.repository.UserRepository;
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

    private static final String UPLOAD_FILE = "/home/ttn/LinkSharing/linkSharing/src/main/resources/static/images/";

//
//    public User createUser(User user){
//        if(userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())){
//            //throw exception here
//            return null;
//        }
//        return userRepository.save(user);
//    }

    public User createUser(@Valid @ModelAttribute User user, MultipartFile file) throws IOException {
        if (file.equals(null) || file.isEmpty()) {
            user.setPhoto("user.getUserName()" + "_" + "User.png");

        }
//        user.setCreatedDate(new Date());
//        user.setUpdatedDate(new Date());
//        user.setActive(true);
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
}
