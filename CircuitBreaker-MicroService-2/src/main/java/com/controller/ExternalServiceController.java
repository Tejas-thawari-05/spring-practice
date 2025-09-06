package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ExternalServiceController {

	@Autowired
	private UserRepository repository;
	
	 @PostMapping("/")
	    public void saveUser(@RequestBody UserEntity user) {
	    	repository.save(user);
	    }
	
	@GetMapping("/data")
    public ResponseEntity<List<UserEntity>> getData() {
		List<UserEntity> user = new ArrayList<>();
		repository.findAll().forEach(user :: add);
        return new ResponseEntity<List<UserEntity>>(user,HttpStatus.OK);
    }
}
