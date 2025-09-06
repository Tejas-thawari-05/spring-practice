package com.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AutoHealingService {

    @Autowired
    private AnomalyDetectionService anomalyService;

    @Scheduled(fixedRate = 10000)
    public void monitorSystem() {
        double simulatedResponseTime = 100 + new Random().nextDouble() * 200;

        if (anomalyService.detectAnomaly(simulatedResponseTime)) {
            System.out.println("🔴 Anomaly detected! Initiating auto-healing...");
            autoScale();
        } else {
            System.out.println("🟢 System healthy. Response time: " + simulatedResponseTime);
        }
    }

    private void autoScale() {
        // Simulation of scale-up or restart
        System.out.println("⚙️ Auto-scaling triggered: Spinning up new instances...");
    }
}