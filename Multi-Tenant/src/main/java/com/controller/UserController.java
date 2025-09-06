package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.TenantContext;
import com.entity.User;
import com.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private UserService userService;

	@PostMapping
    public User createUser(@RequestHeader("X-Tenant-ID") String tenant, @RequestBody User user) {
        TenantContext.setCurrentTenant(tenant);
        return userService.addUser(user);
    }
	
    @GetMapping
    public List<User> getUsers(@RequestHeader("X-Tenant-ID") String tenant) {
        TenantContext.setCurrentTenant(tenant);
        return userService.getUsers();
    }

    
}

