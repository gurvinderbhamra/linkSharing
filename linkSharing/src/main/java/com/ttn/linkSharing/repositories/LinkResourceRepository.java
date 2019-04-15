package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.LinkResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkResourceRepository extends CrudRepository<LinkResource, Long> {
    @Query("select l from LinkResource l where l.description like %:searchText%")
    List<LinkResource> searchLinkResource(@Param("searchText") String searchText);
}
