package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsernameOrEmail(String username, String email);

    Optional<User> findById(Long userId);
}
