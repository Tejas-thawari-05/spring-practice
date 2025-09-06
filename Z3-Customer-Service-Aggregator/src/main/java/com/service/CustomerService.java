package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CustomerEntity;
import com.repository.CustomerRepository;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public CustomerEntity addCustomer(CustomerEntity entity) {
		return customerRepository.save(entity);
	}
	
	
	@CircuitBreaker(name= "customerService" , fallbackMethod = "customerFallback")
	@Bulkhead(name= "customerService" , fallbackMethod = "customerFallback")
	public CustomerEntity getCustomerById(String id) {
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found"));
	}
	
	public CustomerEntity customerFallback(String id, Throwable t) {
        return new CustomerEntity(id,"Fallback User", "fallback@example.com");
    }

}
