package com.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Dto.CustomerEntity;
import com.Dto.PaymentEntity;
import com.entity.OrderEntity;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	
	@Autowired
	private WebClient.Builder webBuilder;
	
	private static final String CUSTOMER_SERVICE_URL = "http://localhost:8080/customer/";
	private static final String PAYMENT_SERVICE_URL = "http://localhost:8081/payment/";
	
	@CircuitBreaker(name = "customerService", fallbackMethod = "customerFallback")
    @Bulkhead(name = "customerService", fallbackMethod = "customerFallback")
    public CompletableFuture<CustomerEntity> getCustomerAsync(String customerId) {
        return webBuilder.build()
                .get()
                .uri(CUSTOMER_SERVICE_URL + customerId)
                .retrieve()
                .bodyToMono(CustomerEntity.class)
                .toFuture();
    }
	
	@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    @Bulkhead(name = "paymentService", fallbackMethod = "paymentFallback")
    public CompletableFuture<PaymentEntity> getPaymentAsync(String customerId) {
        return webBuilder.build()
                .get()
                .uri(PAYMENT_SERVICE_URL + customerId)
                .retrieve()
                .bodyToMono(PaymentEntity.class)
                .toFuture();
    }
	
	
	
	
	
	
	public CompletableFuture<OrderEntity> placeOrder(String customerId) {
        kafkaTemplate.send("order-topic", customerId);
        CompletableFuture<CustomerEntity> customerFuture = getCustomerAsync(customerId);
        CompletableFuture<PaymentEntity> paymentFuture = getPaymentAsync(customerId);

        return customerFuture.thenCombine(paymentFuture, 
            (customer, payment) -> new OrderEntity(customerId, customer, payment));
    }
	
	public CompletableFuture<CustomerEntity> customerFallback(String customerId, Throwable t) {
        return CompletableFuture.completedFuture(new CustomerEntity(customerId, "Fallback User", "fallback@example.com"));
    }

    public CompletableFuture<PaymentEntity> paymentFallback(String customerId, Throwable t) {
        return CompletableFuture.completedFuture(new PaymentEntity(customerId, "N/A", 0.00));
    }
}
