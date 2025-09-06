package com.controller;



import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logEntry.LogEntry;
import com.service.AnomalyDetectionService;

import io.micrometer.core.instrument.MeterRegistry;

@RestController

@RequestMapping("/monitor")
public class MonitoringController {

	@Autowired
    private AnomalyDetectionService anomalyService;
    
    private final MeterRegistry registry;
    private final Random random = new Random();

    public MonitoringController(MeterRegistry registry) {
        this.registry = registry;
    }


    
    @PostMapping("/check")
    public String check(@RequestBody LogEntry logEntry) {
        boolean anomaly = anomalyService.isAnomalous(logEntry);

        if (anomaly) {
            return "⚠️ Anomaly Detected! Initiating self-healing...";
        }
        return "✅ System is healthy.";
    }
    
    
    @GetMapping("/simulateLoad")
    public String simulateLoad() {
        double responseTime = 50 + random.nextDouble() * 150;
        registry.gauge("custom.response.time", responseTime);
        return "Simulated load with response time: " + responseTime;
    }
}

