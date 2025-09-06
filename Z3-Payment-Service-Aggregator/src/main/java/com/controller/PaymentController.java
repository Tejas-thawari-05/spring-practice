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

import com.entity.PaymentEntity;
import com.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/")
	public ResponseEntity<PaymentEntity> addPayment(@RequestBody PaymentEntity entity){
		return new ResponseEntity<PaymentEntity>(paymentService.addPayment(entity),HttpStatus.CREATED);
	}
	
	 @GetMapping("/{customerId}")
	    public ResponseEntity<PaymentEntity> getPayment(@PathVariable String customerId) {
	        return ResponseEntity.ok(paymentService.getPaymentByCustomerId(customerId));
	    }
}
