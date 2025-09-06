package com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.model.QueryAnalysis;
import com.repository.QueryAnalysisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueryOptimizerService {

    private final JdbcTemplate jdbcTemplate;
    private final QueryAnalysisRepository analysisRepository;

   
    public QueryAnalysis analyzeQuery(String query) {
        query = query.trim().replaceAll("^\"|\"$", ""); 

        long startTime = System.currentTimeMillis();

        // Fetch Execution Plan
        List<String> planLines = jdbcTemplate.query("EXPLAIN ANALYZE " + query,
            (rs, rowNum) -> rs.getString(1));

        String executionPlan = String.join("\n", planLines);
        long executionTime = System.currentTimeMillis() - startTime;

        // Store log
        QueryAnalysis log = QueryAnalysis.builder()
                .query(query)
                .executionTime(executionTime)
                .executionPlan(executionPlan)
                .executedAt(LocalDateTime.now())
                .build();

        analysisRepository.save(log);
        return log;
    }

    
    public String optimizeQuery(String query) {
        if (query.toLowerCase().contains("select *")) {
            return query.replaceAll("(?i)select \\*", "SELECT id, name, position, salary");
        }
        return query;
    }

   
    public List<QueryAnalysis> getSlowQueries() {
        return analysisRepository.findTop5ByOrderByExecutionTimeDesc();
    }
}
