package com.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.entity.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;
    private static final String ORDER_TOPIC = "order-topic";

    public void sendOrder(Order order) {
        kafkaTemplate.send(ORDER_TOPIC, order.getOrderId(), order);
    }
}