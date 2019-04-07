package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    public User login(String username, String password){
        User user = userRepository.findByUsernameOrEmail(username, username);
        if(user == null || !(user.getPassword().equals(password))){
            //invalid username or password
            return null;
        }
        else{
            return user;
        }
    }
}
