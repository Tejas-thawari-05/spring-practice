package com.serviceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Subject;
import com.repository.SubjectRepository;
import com.services.QuestionClient;
import com.services.SubjectService;

@Service
public class SubjectService_Impl implements SubjectService {

	private SubjectRepository subjectRepository;

	public SubjectService_Impl(SubjectRepository subjectRepository) {
		super();
		this.subjectRepository = subjectRepository;
	}

	@Autowired
	private QuestionClient queClient;

	@Override
	public Subject add(Subject subject) {

		return subjectRepository.save(subject);
	}

	@Override
	public List<Subject> get() {

		List<Subject> subjects = subjectRepository.findAll();

		List<Subject> newSubjectList = subjects.stream().map(subject -> {
			subject.setQuestion(queClient.getQuestionOfSubject(subject.getId()));
			return subject;
		}).collect(Collectors.toList());

		return newSubjectList;
	}

	@Override
	public Subject get(long id) {
		
		Subject subject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not Found..."));
		subject.setQuestion(queClient.getQuestionOfSubject(subject.getId()));
		return subject;
	}

}
