package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Subject;
import com.services.SubjectService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/subject")
public class SubjectController {

	 @Autowired
	 private SubjectService subjectService;
	 
	 private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);
	 
	 @PostMapping
	 public Subject create(@RequestBody Subject subject) {
		 return subjectService.add(subject);
	 }
	 
	 @GetMapping
	 public List<Subject> get(){
		 return subjectService.get();
	 }
	 
	 @CircuitBreaker(name = "USER", fallbackMethod = "randomMethod")
	    @GetMapping("/{id}")
	    public Subject getSubjectById(@PathVariable long id) {
	        return subjectService.get(id);
	    }

	    
	    public Subject randomMethod(long id, Throwable e) {  // Remove @PathVariable
	        logger.warn("Fallback executed due to service down: " + e.getMessage());
	        return new Subject(id, "Service Down", null);  // Ensure Subject constructor matches
	    }
}
