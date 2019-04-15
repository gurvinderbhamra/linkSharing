package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    public User getUserByEmail(String email){
        return userRepository.findByUsernameOrEmail(email, email);
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

    public User updatePassword(User user){
        return userRepository.save(user);
    }

    public void updateActivation(User user){
        userRepository.save(user);
    }

    public List<User> searchUser(String search){
        return userRepository.searchUsers(search);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
