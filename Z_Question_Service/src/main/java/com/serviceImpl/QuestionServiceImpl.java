package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Question;
import com.repository.QuestionRepository;
import com.service.Question_Service;

@Service
public class QuestionServiceImpl implements Question_Service{

	@Autowired
	private QuestionRepository queRepository;
	
	@Override
	public Question create(Question question) {
		
		return queRepository.save(question);
	}

	@Override
	public List<Question> get() {
		
		return queRepository.findAll();
	}

	@Override
	public Question getQuestionById(Long id) {
		return queRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found..."));
	}

	@Override
	public List<Question> getQuestionOfSubject(Long subjectId) {
		return queRepository.findBySubjectId(subjectId);
	}

	

	

}
