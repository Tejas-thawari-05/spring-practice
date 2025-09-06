package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableCircuitBreaker
public class CircuitBreakerMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerMicroServiceApplication.class, args);
	}

}
