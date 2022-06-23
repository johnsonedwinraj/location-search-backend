package com.wisestep.locationsearch.controller;

import com.wisestep.locationsearch.dto.LocationRequest;
import com.wisestep.locationsearch.dto.LocationResponse;
import com.wisestep.locationsearch.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    private final LocationService locationService;


    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<LocationResponse> save(@RequestBody LocationRequest locationRequest) {

        return new ResponseEntity<>(locationService.save(locationRequest), HttpStatus.OK);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<LocationResponse>> search(@RequestParam("query") String query) {

        return new ResponseEntity<>(locationService.search(query), HttpStatus.OK);
    }

}
