package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    Optional<Topic> findById(Long topicId);
}
