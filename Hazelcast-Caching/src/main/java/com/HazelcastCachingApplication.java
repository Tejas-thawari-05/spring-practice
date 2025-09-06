package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HazelcastCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastCachingApplication.class, args);
	}

}
