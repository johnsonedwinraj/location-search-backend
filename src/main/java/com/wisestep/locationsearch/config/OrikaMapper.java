package com.wisestep.locationsearch.config;

import com.wisestep.locationsearch.dto.LocationRequest;
import com.wisestep.locationsearch.dto.LocationResponse;
import com.wisestep.locationsearch.entity.Locations;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class OrikaMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(Locations.class, LocationResponse.class)
            .byDefault()
            .register();

        factory.classMap(LocationRequest.class, Locations.class)
            .byDefault()
            .register();
    }
}
