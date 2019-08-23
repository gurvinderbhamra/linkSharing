package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsernameOrEmail(String username, String email);

    Optional<User> findById(Long userId);

    Boolean existsByUsernameOrEmail(String username, String email);

    @Query("select u from User u where u.firstName like %:searchText% " +
            "or u.LastName like %:searchText% " +
            "or u.username like %:searchText%")
    List<User> searchUsers(@Param("searchText") String searchText);
}
