package com.example.sentra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SentraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentraApplication.class, args);
	}

}
