package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NlpSentimentAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(NlpSentimentAnalysisApplication.class, args);
	
//		StanfordCoreNLP coreNLP = Pipeline.getPipeline();
//		
//		String text = "This is tejas thawari";
//		
//		CoreDocument coreDocument = new CoreDocument(text);
//	
//		coreNLP.annotate(coreDocument);
//		
//		List<CoreLabel> CoreLabelList = coreDocument.tokens();
//		
//		for(CoreLabel coLabel : CoreLabelList) {
//			System.out.println(coLabel.originalText());
//		}
	}

}
