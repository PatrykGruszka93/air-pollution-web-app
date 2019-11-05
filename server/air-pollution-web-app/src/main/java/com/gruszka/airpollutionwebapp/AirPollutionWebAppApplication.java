package com.gruszka.airpollutionwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AirPollutionWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirPollutionWebAppApplication.class, args);
	}

}
