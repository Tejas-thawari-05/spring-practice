package com.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ExternalService {

	private static final String SERVICE_NAME = "externalService";
    private final RestTemplate restTemplate;

    public ExternalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackResponse")
    public String getExternalResponse() {
        String url = "http://localhost:8081/api/data"; // URL of another microservice
        //return restTemplate.getForObject(url, String.class);
        
        
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception ex) {
            throw new RuntimeException("Service call failed", ex);  // ✅ Ensure fallback is triggered
        }
    }

    
    public String fallbackResponse(Throwable ex) {
        return "Fallback response: Service is down!";
    }
}
