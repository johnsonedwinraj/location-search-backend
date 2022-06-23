package com.wisestep.locationsearch.repository;

import com.wisestep.locationsearch.entity.Locations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends ElasticsearchRepository<Locations, String> {

    List<Locations> findAllByDisplayName(String displayName);

}
