//package com.example.UserMigrationService;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MigrationPublisher {
//    private final RabbitTemplate rabbitTemplate;
//
//    public MigrationPublisher(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void publishMigration(String sql) {
//        rabbitTemplate.convertAndSend("schema.migration", sql);
//    }
//}
