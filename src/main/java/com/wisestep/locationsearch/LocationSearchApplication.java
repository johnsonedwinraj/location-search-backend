package com.wisestep.locationsearch;

import com.wisestep.locationsearch.config.OrikaMapper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LocationSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationSearchApplication.class, args);
    }

    @Bean
    public MapperFacade orikaMapper() {
        return new OrikaMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
