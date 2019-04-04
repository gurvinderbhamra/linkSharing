package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entity.User;
import com.ttn.linkSharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        if(userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())){
            //throw exception here
            return null;
        }
        return userRepository.save(user);
    }
}
