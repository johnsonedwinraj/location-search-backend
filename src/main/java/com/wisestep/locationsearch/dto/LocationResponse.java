package com.wisestep.locationsearch.dto;

import lombok.Data;

@Data
public class LocationResponse {

    private String placeId;

    private String displayName;

    private String latitude;

    private String longitude;

    private String data;
}
