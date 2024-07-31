package com.Task.ShopClues;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableDynamoDBRepositories(basePackages = "com.Task.ShopClues.Repository")
public class ShopCluesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopCluesApplication.class, args);
	}

}
