package com.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.StepFunctionService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final StepFunctionService stepFunctionService;

    public OrderController(StepFunctionService stepFunctionService) {
        this.stepFunctionService = stepFunctionService;
    }

    @PostMapping("/process")
    public Map<String, String> processOrder(@RequestBody Map<String, Object> orderData) {
        String executionArn = stepFunctionService.startOrderProcessing(orderData);  // ✅ Pass Map directly
        return Map.of("executionArn", executionArn);
    }
    
    
//    @PostMapping("/process")
//    public Map<String, Object> processOrder(@RequestBody Map<String, Object> orderData) {
//        return stepFunctionService.startOrderProcessing(orderData);
//    }
}
