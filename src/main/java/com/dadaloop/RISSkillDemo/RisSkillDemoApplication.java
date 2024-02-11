package com.dadaloop.RISSkillDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * Main entry point of the Spring Boot application.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dadaloop.RISSkillDemo.repository")
@EntityScan(basePackages = "com.dadaloop.RISSkillDemo.model")
public class RisSkillDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(RisSkillDemoApplication.class, args);
	}
}
