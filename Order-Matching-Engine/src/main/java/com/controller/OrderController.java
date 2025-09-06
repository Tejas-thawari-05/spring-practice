package com.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Order;
import com.kafka.OrderProducer;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/submit")
    public String submitOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        order.setTimestamp(System.currentTimeMillis());

        orderProducer.sendOrder(order);
        return "Order submitted to Kafka: " + order.getOrderId();
    }
}