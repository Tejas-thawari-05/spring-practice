package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;

@Configuration
public class Resilience4jConfig {

	 @Bean
	  BulkheadRegistry bulkheadRegistry() {
	        return BulkheadRegistry.of(BulkheadConfig.custom().build());
	    }
	 
	 @Bean
	 RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	 
	 @Bean
	 @Primary
	 ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry() {
	        return ThreadPoolBulkheadRegistry.of(ThreadPoolBulkheadConfig.custom().build());
	    }
}
