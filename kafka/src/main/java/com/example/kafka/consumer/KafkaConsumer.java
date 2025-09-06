package com.example.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "consumer", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}

