package com.example.UserMigrationService.Listener;

import com.example.UserMigrationService.Config.RabbitMQConfig;
import com.example.UserMigrationService.Entity.User;
import com.example.UserMigrationService.Event.MigrationEvent;
import com.example.UserMigrationService.Repository.UserRepository;

import io.getunleash.Unleash;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MigrationListener {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Unleash unleash;
 
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleMigration(MigrationEvent event) {
        if ("add-email".equals(event.getAction())) {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                if (user.getEmail() == null) {
                    user.setEmail(user.getName().toLowerCase() + "@example.com");
                    userRepository.save(user);
                }
            }
            System.out.println("Email field initialized for all users ");
        }
        if ("add-status".equals(event.getAction())) {
            boolean isEnabled = unleash.isEnabled("userMigration"); // 👈 check flag
 
            if (!isEnabled) {
                System.out.println("Feature flag is disabled. Skipping status migration.");
                return;
            }
            List<User> users = userRepository.findAll();
            for (User user : users) {
                if (user.getStatus() == null) {
                    user.setStatus("active"); // Default status
                    userRepository.save(user);
                }
            }
            System.out.println("Status field initialized for all users ");
        }
    }
}