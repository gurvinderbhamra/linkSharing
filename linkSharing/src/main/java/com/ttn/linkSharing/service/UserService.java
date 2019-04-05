package com.ttn.linkSharing.service;

import com.ttn.linkSharing.entity.User;
import com.ttn.linkSharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long userId){
        return userRepository.findByUserId(userId);
    }
}
