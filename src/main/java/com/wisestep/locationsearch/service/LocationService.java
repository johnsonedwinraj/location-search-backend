package com.wisestep.locationsearch.service;

import com.wisestep.locationsearch.dto.LocationRequest;
import com.wisestep.locationsearch.dto.LocationResponse;

import java.util.List;

public interface LocationService {

    /**
     * In this method once client got their result set based on their keywords,
     * they will choose one among from result set, and send back to us to store the information in elastic search engine,
     * that will be used for future searching purpose, to avoid calling external resource again and again.
     *
     * @param locationRequest object which contains detailed information about the location
     * @return Persisted data will be returned with status code.
     */
    LocationResponse save(LocationRequest locationRequest);

    /**
     * In this method we're used to search data based on keyword or phrases in elastic search engine,
     * if the data is not present then we're calling the external resource i.e locationiq.com,
     * once we got the result we send back to client.
     *
     * @param query search keyword or phrases
     * @return Matched Result set
     */
    List<LocationResponse> search(String query);
}
