package com.example.UserMigrationService.Controller;

import com.example.UserMigrationService.Config.RabbitMQConfig;
import com.example.UserMigrationService.Event.MigrationEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/migration")
public class MigrationController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerMigration() {
        MigrationEvent event = new MigrationEvent("add-status"); // Changed this line
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
        return ResponseEntity.ok("Migration Triggered Successfully");
    }
}
