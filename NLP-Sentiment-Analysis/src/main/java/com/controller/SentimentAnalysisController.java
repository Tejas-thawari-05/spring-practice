package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.SentimentAnalysis;
import com.service.SentimentAnalysisService;

@RestController
@RequestMapping("/api")
public class SentimentAnalysisController {

	@Autowired
	private SentimentAnalysisService service;
	
	@PostMapping("/analyze")
	public SentimentAnalysis analyzeText(@RequestBody String text) {
		return service.analysis(text);
	}
	
	@GetMapping("/history")
	public List<SentimentAnalysis> getAnalysisHistory(){
		return service.getAllSentiments();
	}
}
