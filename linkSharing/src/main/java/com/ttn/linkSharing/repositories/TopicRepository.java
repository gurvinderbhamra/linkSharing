package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    Optional<Topic> findById(Long topicId);
    Optional<Topic> findByTopicName(String topicName);
    Integer countByCreatedByLike(String createdBy);
    void deleteById(Long id);
    boolean existsById(Long topicId);

    @Query("select t from Topic t where t.topicName like %:searchText%")
    List<Topic> findTopics(@Param("searchText") String searchText);
}
