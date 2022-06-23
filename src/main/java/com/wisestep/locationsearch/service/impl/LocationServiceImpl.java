package com.wisestep.locationsearch.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisestep.locationsearch.dto.LocationRequest;
import com.wisestep.locationsearch.dto.LocationResponse;
import com.wisestep.locationsearch.entity.Locations;
import com.wisestep.locationsearch.exception.ServiceException;
import com.wisestep.locationsearch.repository.LocationRepository;
import com.wisestep.locationsearch.service.LocationService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wisestep.locationsearch.util.Constants.*;


@Service
public class LocationServiceImpl implements LocationService {

    @Value("${external.resource.url}")
    private String externalResourceURL;

    private final MapperFacade mapperFacade;
    private final LocationRepository locationRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public LocationServiceImpl(MapperFacade mapperFacade, LocationRepository locationRepository, RestTemplate restTemplate) {
        this.mapperFacade = mapperFacade;
        this.locationRepository = locationRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public LocationResponse save(LocationRequest locationReq) {

        Locations locations = locationRepository.save(mapperFacade.map(locationReq, Locations.class));
        return mapperFacade.map(locations, LocationResponse.class);
    }

    @Override
    public List<LocationResponse> search(String queryString) {

        List<Locations> locations = locationRepository.findAllByDisplayName(queryString);
        if (null != locations && !locations.isEmpty()) return mapperFacade.mapAsList(locations, LocationResponse.class);
        else return callExternalResources(queryString);
    }

    private List<LocationResponse> callExternalResources(String queryString) {

        ResponseEntity<List<Map<String, Object>>> responseEntity = clientCaller(queryString);
        if (HttpStatus.OK == responseEntity.getStatusCode() && null != responseEntity.getBody() && !responseEntity.getBody().isEmpty()) {
            List<Map<String, Object>> responseBody = responseEntity.getBody();
            return responseBody.stream().map(this::constructLocation).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private LocationResponse constructLocation(Map<String, Object> response) {
        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setPlaceId((String) response.get("place_id"));
        locationResponse.setDisplayName((String) response.get("display_name"));
        locationResponse.setLatitude((String) response.get("lat"));
        locationResponse.setLongitude((String) response.get("lon"));
        try {
            locationResponse.setData(new ObjectMapper().writeValueAsString(response));
            return locationResponse;
        } catch (JsonProcessingException e) {
            throw new ServiceException(FAILED, ERROR_MESSAGE);
        }
    }

    private ResponseEntity<List<Map<String, Object>>> clientCaller(String queryString) {
        String externalResourceLink = externalResourceURL + EXTERNAL_RESOURCE_TOKEN + "&q=" + queryString;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        Map<String, Object> requestBody = new HashMap<>();
        HttpEntity<?> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
        return restTemplate.exchange(externalResourceLink, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
        });
    }
}
