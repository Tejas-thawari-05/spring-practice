package com.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.logEntry.LogEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Service
public class AnomalyDetectionService {

    private static final int MAX_HISTORY = 30;
    private final Queue<LogEntry> history = new LinkedList<>();
    
    private final List<Double> responseTimes = new ArrayList<>();
    private final int MAX_SIZE = 100;

    public boolean isAnomalous(LogEntry entry) {
        if (history.size() >= MAX_HISTORY) {
            history.poll(); // Remove oldest
        }
        history.add(entry);

        double avgCpu = history.stream().mapToDouble(LogEntry::getCpuUsage).average().orElse(0);
        double avgMem = history.stream().mapToDouble(LogEntry::getMemoryUsage).average().orElse(0);

        boolean cpuSpike = entry.getCpuUsage() > avgCpu * 1.5;
        boolean memSpike = entry.getMemoryUsage() > avgMem * 1.5;
        boolean isError = entry.isError();

        log.info("CPU: {}, MEM: {}, AVG_CPU: {}, AVG_MEM: {}", entry.getCpuUsage(), entry.getMemoryUsage(), avgCpu, avgMem);

        return cpuSpike || memSpike || isError;
    }
    
    
    public boolean detectAnomaly(double currentTime) {
        if (responseTimes.size() >= MAX_SIZE) {
            responseTimes.remove(0);
        }
        responseTimes.add(currentTime);

        double[] data = responseTimes.stream().mapToDouble(Double::doubleValue).toArray();
        double mean = Arrays.stream(data).average().orElse(0);
        double stdDev = Math.sqrt(Arrays.stream(data).map(val -> Math.pow(val - mean, 2)).average().orElse(0));

        return currentTime > mean + 2 * stdDev;  // simple z-score
    }
}
