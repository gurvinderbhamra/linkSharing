package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.UserSubscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {
    Optional<UserSubscription> findById(Long id);
}
