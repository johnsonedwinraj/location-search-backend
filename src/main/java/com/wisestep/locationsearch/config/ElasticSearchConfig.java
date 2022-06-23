package com.wisestep.locationsearch.config;

import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.wisestep.locationsearch.repository"})
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {


    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo("49.204.127.241:9200")
            .withConnectTimeout(5000)
            .withSocketTimeout(60000)
            .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
