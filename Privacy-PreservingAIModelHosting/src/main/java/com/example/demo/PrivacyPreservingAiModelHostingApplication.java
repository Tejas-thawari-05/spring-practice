package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.nt.util.MPCTrainer;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EntityScan("com.nt.model")
@ComponentScan(basePackages= {"com.nt.controller","com.nt.service","com.nt.security","com.nt.util"})
public class PrivacyPreservingAiModelHostingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivacyPreservingAiModelHostingApplication.class, args);
	}
	
	
	 @PostConstruct
	    public void init() {
	        MPCTrainer.trainSecureModel();
	    }

}
