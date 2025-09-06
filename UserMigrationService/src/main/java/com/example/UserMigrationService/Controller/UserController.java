package com.example.UserMigrationService.Controller;

import com.example.UserMigrationService.Entity.User;
import com.example.UserMigrationService.Repository.UserRepository;
import io.getunleash.Unleash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Unleash unleash;

    @Value("${app.version}")
    private String appVersion;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
    @GetMapping("/unleash")
    public List<User> getUsers() {
        boolean showStatus = unleash.isEnabled("NewStatusField");

        List<User> users = userRepository.findAll();

        if (!showStatus) {
            for (User user : users) {
                user.setStatus(null); // Hide status field dynamically
            }
        }

        return users;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/version")
    public String getVersion() {
        return "Current Version: " + appVersion;
    }
}
