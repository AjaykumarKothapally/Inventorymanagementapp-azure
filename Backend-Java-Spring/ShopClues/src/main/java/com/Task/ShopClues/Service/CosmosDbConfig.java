package com.Task.ShopClues.Service;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosAsyncClient;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.CosmosFactory;
import com.azure.spring.data.cosmos.core.convert.MappingCosmosConverter;
import com.azure.spring.data.cosmos.core.mapping.CosmosMappingContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosmosDbConfig {

    @Value("${cosmosdb.endpoint}")
    private String endpoint;

    @Value("${cosmosdb.key}")
    private String key;

    @Value("${cosmosdb.databaseName}")
    private String databaseName;

    @Bean
    public CosmosAsyncClient cosmosAsyncClient() {
        // Add version-checking code here
        String version = System.getProperty("java.version");
        if (version.contains("-")) {
            version = version.split("-")[0];
        }
        int javaVersion = Integer.parseInt(version);

        // Use the parsed version if needed or simply log it
        System.out.println("Java Version: " + javaVersion);

        return new CosmosClientBuilder()
                .endpoint(endpoint)
                .key(key)
                .directMode()
                .buildAsyncClient();
    }

    @Bean
    public CosmosMappingContext cosmosMappingContext() {
        return new CosmosMappingContext();
    }

    @Bean
    public CosmosFactory cosmosFactory(CosmosAsyncClient cosmosAsyncClient) {
        return new CosmosFactory(cosmosAsyncClient, databaseName);
    }

    @Bean
    public CosmosTemplate cosmosTemplate(CosmosFactory cosmosFactory, MappingCosmosConverter mappingCosmosConverter) {
        return new CosmosTemplate(cosmosFactory, cosmosConfig(), mappingCosmosConverter);
    }

    @Bean
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder().enableQueryMetrics(true).build();
    }

    @Bean
    public MappingCosmosConverter mappingCosmosConverter(CosmosMappingContext cosmosMappingContext,
            ObjectMapper objectMapper) {
        return new MappingCosmosConverter(cosmosMappingContext, objectMapper);
    }
}
