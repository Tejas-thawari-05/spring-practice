package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Question;
import com.service.Question_Service;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private Question_Service queService;
	
	@PostMapping
	public Question create(@RequestBody Question question) {
		return queService.create(question);
	}
	
	@GetMapping
	public List<Question> getAll(){
		return queService.get();
	}
	
	@GetMapping("/{questionId}")
	public Question getQuestionById(@PathVariable Long questionId) {
		return queService.getQuestionById(questionId);
	}
	
	@GetMapping("/subject/{id}")
	public List<Question> getQuestionOfSubject(@PathVariable Long id){
		return queService.getQuestionOfSubject(id);
	}
}
