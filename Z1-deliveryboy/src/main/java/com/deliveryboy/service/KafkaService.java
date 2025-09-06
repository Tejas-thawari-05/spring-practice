package com.deliveryboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean startLocationUpdate(String location) {
        kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, "LOCATION_UPDATE_STARTED: " + location);
        return true;
    }

    public void completeLocationUpdate(String location) {
        kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, "LOCATION_UPDATE_SUCCESS: " + location);
    }

    public void rollbackLocationUpdate(String location) {
        kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, "LOCATION_UPDATE_FAILED: " + location);
    }
}
