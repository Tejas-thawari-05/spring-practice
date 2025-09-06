package com.service;

import java.util.List;

import com.entity.Question;

public interface Question_Service {

	Question create(Question question);
	
	List<Question> get();
	
	public Question getQuestionById(Long id);
	
	public List<Question> getQuestionOfSubject(Long subjectId);
}
