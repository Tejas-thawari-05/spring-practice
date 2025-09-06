package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.PaymentEntity;
import com.repository.PaymentRepository;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentEntity addPayment(PaymentEntity entity) {
		return paymentRepository.save(entity);
	}
	
	@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    @Bulkhead(name = "paymentService", fallbackMethod = "paymentFallback")
    public PaymentEntity getPaymentByCustomerId(String customerId) {
        return paymentRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));
    }

    public PaymentEntity paymentFallback(String customerId, Throwable t) {
        return new PaymentEntity(customerId, "N/A", 0.00);
    }
}