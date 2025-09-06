package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.entity.CustomerEntity;
import com.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerKafkaListener {

	@Autowired
	private CustomerService customerService;
	
	@KafkaListener(topics = "order-topic", groupId = "customer-group")
	public void consumeCustomerData(String id) {
		log.info("Received kafka event for Customer id : "+id);
		
		CustomerEntity customer = customerService.getCustomerById(id);
		log.info("Processed Customer : {} "+customer);
	}
}
