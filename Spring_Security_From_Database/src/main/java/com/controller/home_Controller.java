package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repository.Emp_Repository;

@RestController

public class home_Controller {

	@Autowired
	private Emp_Repository empRepository;
	
	@GetMapping("/")
	public String index() {
		return "Index page loaded..";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about page loaded..";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile page loaded..";
	}
}
