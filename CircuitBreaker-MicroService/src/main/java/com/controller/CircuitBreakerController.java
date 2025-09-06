package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.ExternalService;

@RestController
@RequestMapping("/api")
public class CircuitBreakerController {

	
	
	 private final ExternalService externalService;

	    public CircuitBreakerController(ExternalService externalService) {
	        this.externalService = externalService;
	    }
	    
	    
	    @GetMapping("/fetch-data")
	    public String fetchData() {
	        return externalService.getExternalResponse();
	    }
}
