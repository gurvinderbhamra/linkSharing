package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.DocumentResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentResourceRepository extends CrudRepository<DocumentResource, Long> {
    @Query("select d from DocumentResource d where d.description like %:searchText%")
    List<DocumentResource> searchDocument(@Param("searchText") String searchText);
}
