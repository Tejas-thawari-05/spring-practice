package com.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentEntity {

	
	private String customerId;
	
	private String paymentMode;
	
	private double amount;

	
}

