package com.Task.ShopClues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableCosmosRepositories(basePackages = "com.Task.ShopClues.Repository")
public class ShopCluesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShopCluesApplication.class, args);
	}
}
