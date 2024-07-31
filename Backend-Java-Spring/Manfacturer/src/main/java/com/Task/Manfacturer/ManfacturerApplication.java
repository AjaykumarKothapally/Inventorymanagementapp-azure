package com.Task.Manfacturer;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableDynamoDBRepositories(basePackages = "com.Task.Manfacturer.Repository")
public class ManfacturerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManfacturerApplication.class, args);
	}
}
