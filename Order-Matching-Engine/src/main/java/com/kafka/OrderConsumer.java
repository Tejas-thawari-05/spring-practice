package com.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.entity.Order;
import com.service.OrderMatchingEngine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final OrderMatchingEngine orderMatchingEngine;

    @KafkaListener(topics = "order-topic", groupId = "order-matching-group")
    public void consumeOrder(Order order) {
        log.info("Received Order: {}", order);
        orderMatchingEngine.processOrder(order);
    }
}