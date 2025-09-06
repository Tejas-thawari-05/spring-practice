package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.entity.PaymentEntity;
import com.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentKafkaListerner {
	
	@Autowired
	private PaymentService paymentService;

	 @KafkaListener(topics = "order-topic", groupId = "payment-group")
	    public void processPayment(String customerId) {
	        log.info("Received Kafka event for customer ID: " + customerId);

	        PaymentEntity payment = paymentService.getPaymentByCustomerId(customerId);
	        log.info("Processed Payment: {}", payment);
	    }
	}
