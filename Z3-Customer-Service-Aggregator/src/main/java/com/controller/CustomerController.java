package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/")
	public ResponseEntity<CustomerEntity> addCustomer(@RequestBody CustomerEntity entity){
		return new ResponseEntity<CustomerEntity>(customerService.addCustomer(entity),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerEntity> getMethodName(@PathVariable String id) {
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}
	
}
