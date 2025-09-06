package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.EmailEntity;
import com.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	@PostMapping("/sendEmail")
	public String sendMail(@ModelAttribute EmailEntity email,HttpSession session) {
		
		emailService.sendEmail(email);
		session.setAttribute("msg", "Email Send Successfully...");
		
		return "redirect:/";
	}
}
