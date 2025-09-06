package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String>{

}
