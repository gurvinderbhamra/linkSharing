package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsernameOrEmail(String username, String email);

    User findByUserId(Long userId);
}
