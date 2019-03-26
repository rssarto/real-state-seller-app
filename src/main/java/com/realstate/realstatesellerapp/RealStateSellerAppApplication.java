package com.realstate.realstatesellerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class RealStateSellerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealStateSellerAppApplication.class, args);
	}

}
