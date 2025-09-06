package com.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.OrderEntity;
import com.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	 @GetMapping("/{customerId}")
	    public CompletableFuture<ResponseEntity<OrderEntity>> placeOrder(@PathVariable String customerId) {
	        return orderService.placeOrder(customerId)
	                .thenApply(ResponseEntity::ok);
	    }
}
