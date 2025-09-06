package com.services;

import java.util.List;

import com.entity.Subject;

public interface SubjectService {

	Subject add(Subject subject);
	
	List<Subject> get();
	
	Subject get(long id);
}
