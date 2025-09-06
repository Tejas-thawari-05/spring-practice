package com.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.entity.SentimentAnalysis;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.SentimentAnalysisRepository;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;

@Service
public class SentimentAnalysisService {

	
	private final SentimentAnalysisRepository analysisRepository;
	private final StanfordCoreNLP pipeline;
	
	 public SentimentAnalysisService(SentimentAnalysisRepository repository) {
	        this.analysisRepository = repository;
	        
	        Properties props = new Properties();
	        
	        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
	       
	        this.pipeline = new StanfordCoreNLP(props);
	    }
	 
	 public SentimentAnalysis analysis(String text) {
		 
		 text = extractCleanText(text);
		 
		 Annotation annotation = new Annotation(text);
		 
		 pipeline.annotate(annotation);
		 
		 String sentiment = annotation.get(CoreAnnotations.SentencesAnnotation.class)
				 .stream()
				 .map(sentence -> sentence.get(SentimentCoreAnnotations.SentimentClass.class))
				 .findFirst()
				 .orElse("Neutral");
		 
		 SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
		 sentimentAnalysis.setText(text);
		 sentimentAnalysis.setSentiment(sentiment);
		 
		 return analysisRepository.save(sentimentAnalysis);
	 }
	 
	 public List<SentimentAnalysis> getAllSentiments() {
	        return analysisRepository.findAll();
	    }
	
	 
	 
	 private String extractCleanText(String text) {
		    text = text.trim();

		    // Detect if input is a JSON object
		    if (text.startsWith("{") && text.endsWith("}")) {
		        try {
		            ObjectMapper objectMapper = new ObjectMapper();
		            // Convert JSON string to a map
		            var map = objectMapper.readValue(text, new TypeReference<Map<String, String>>() {});
		            // Extract the "text" field
		            text = map.getOrDefault("text", text);  // Default to original text if "text" key is missing
		        } catch (Exception e) {
		            System.out.println("Error parsing text JSON: " + e.getMessage());
		        }
		    }
		    
		    return text;
		}

	
}
