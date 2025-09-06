package com.deliveryboy.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaSagaConsumer {

    @KafkaListener(topics = AppConstants.LOCATION_TOPIC_NAME, groupId = "location-group")
    public void handleSagaEvents(ConsumerRecord<String, String> record) {
        String message = record.value();
        System.out.println("Received Kafka Message: " + message);

        if (message.startsWith("LOCATION_UPDATE_FAILED")) {
            System.out.println("Rolling back transaction...");
            
        }
    }
}
