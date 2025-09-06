package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.SentimentAnalysis;

public interface SentimentAnalysisRepository extends JpaRepository<SentimentAnalysis, Integer>{

}
