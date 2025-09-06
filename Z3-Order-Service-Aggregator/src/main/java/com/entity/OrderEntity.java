package com.entity;

import com.Dto.CustomerEntity;
import com.Dto.PaymentEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

	private String customerId;
	
	private CustomerEntity customer;
	
	private PaymentEntity payment;
}
