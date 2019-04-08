package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Topic findByTopicId(Long topicId);
}
