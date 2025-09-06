package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.model.QueryAnalysis;
import com.service.QueryOptimizerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/query")
@RequiredArgsConstructor
public class QueryController {

    private final QueryOptimizerService queryOptimizerService;

    @PostMapping("/analyze")
    public QueryAnalysis analyzeQuery(@RequestBody String query) {
        return queryOptimizerService.analyzeQuery(query);
    }

    @PostMapping("/optimize")
    public String optimizeQuery(@RequestBody String query) {
        return queryOptimizerService.optimizeQuery(query);
    }

    @GetMapping("/slow-queries")
    public List<QueryAnalysis> getSlowQueries() {
        return queryOptimizerService.getSlowQueries();
    }
}
