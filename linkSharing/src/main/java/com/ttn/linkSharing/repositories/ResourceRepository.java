package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
}
