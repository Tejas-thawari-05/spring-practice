package com.logEntry;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {
    private long timestamp;
    private double cpuUsage;
    private double memoryUsage;
    private boolean error;
}


