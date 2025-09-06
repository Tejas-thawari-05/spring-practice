package com.entity;

public class Question {

	private Long questionId;
	
	private String question;
	
	private Long subjectId;
	

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long quizId) {
		this.subjectId = quizId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Question(Long questionId, String question, Long subjectId) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.subjectId = subjectId;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", question=" + question + "]";
	}
	
	 
}

