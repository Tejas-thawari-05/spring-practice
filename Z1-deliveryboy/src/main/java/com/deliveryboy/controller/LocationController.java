package com.deliveryboy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryboy.service.KafkaService;

@RestController
@RequestMapping("/location")
public class LocationController {
	
    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation() {
    	
    	
        String location = "(" + Math.round(Math.random() * 100) + ", " + Math.round(Math.random() * 100) + ")";
        
        kafkaService.startLocationUpdate(location);
        
    	
        if (Math.random() > 0.5) {
            kafkaService.completeLocationUpdate(location);
            return new ResponseEntity<>(Map.of("message", "Location update successful"), HttpStatus.OK);
        } else {
            kafkaService.rollbackLocationUpdate(location);
            return new ResponseEntity<>(Map.of("message", "Location update failed. Rollback triggered"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
