package com.dadaloop.RISSkillDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * Main entry point of the Spring Boot application.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dadaloop.RISSkillDemo.repository")
@ComponentScan("com.dadaloop.RISSkillDemo")
public class RisSkillDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(RisSkillDemoApplication.class, args);
	}
}
