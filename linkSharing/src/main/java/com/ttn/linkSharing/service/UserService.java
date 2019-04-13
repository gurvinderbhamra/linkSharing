package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    public User updateUser(User existingUser, User user){
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUsername(user.getUsername());
        if(user.getPhoto() != null){
            existingUser.setPhoto(user.getPhoto());
        }
        return userRepository.save(existingUser);
    }
}
