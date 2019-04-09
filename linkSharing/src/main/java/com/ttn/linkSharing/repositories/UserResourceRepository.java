package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.UserResource;
import org.springframework.data.repository.CrudRepository;

public interface UserResourceRepository extends CrudRepository<UserResource, Long> {
}
