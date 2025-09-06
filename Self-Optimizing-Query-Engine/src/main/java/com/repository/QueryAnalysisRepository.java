package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.QueryAnalysis;

public interface QueryAnalysisRepository extends JpaRepository<QueryAnalysis, Long>{

	List<QueryAnalysis> findTop5ByOrderByExecutionTimeDesc();
}
