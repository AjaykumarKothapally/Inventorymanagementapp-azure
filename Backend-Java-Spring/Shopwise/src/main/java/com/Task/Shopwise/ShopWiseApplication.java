package com.Task.Shopwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableDynamoDBRepositories(basePackages = "com.Task.Shopwise.Repository")
public class ShopWiseApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShopWiseApplication.class, args);
	}
}