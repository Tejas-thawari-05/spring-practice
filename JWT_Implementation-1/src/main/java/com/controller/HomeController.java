package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.UserRequest;
import com.service.User_Service;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

	@Autowired
	private User_Service user_Service;
	
	@GetMapping("/")
	public ResponseEntity<?> getDetail(HttpServletRequest  request){
		//String id = request.getSession().getId();
		return new ResponseEntity<>("Hello, Welcome to home page... ",HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserDetail(HttpServletRequest  request){
		
		return new ResponseEntity<>(user_Service.getUserDetails(),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
		
		String token = user_Service.login(userRequest);
		if(token == null) {
			return new ResponseEntity<>("Invalid Credentaial...",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(token,HttpStatus.OK);
	}
}
