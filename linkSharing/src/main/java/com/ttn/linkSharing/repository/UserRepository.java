package com.ttn.linkSharing.repository;

import com.ttn.linkSharing.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsernameOrEmail(String username, String email);
    boolean existsByUsernameOrEmail(String username, String email);
    User findByUserId(Long userId);
}
