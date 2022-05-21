package com.example.accenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AccentureApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccentureApplication.class, args);
	}

}
