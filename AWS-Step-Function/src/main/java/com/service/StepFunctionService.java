package com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sfn.SfnClient;
import software.amazon.awssdk.services.sfn.model.DescribeExecutionRequest;
import software.amazon.awssdk.services.sfn.model.DescribeExecutionResponse;
import software.amazon.awssdk.services.sfn.model.ExecutionStatus;
import software.amazon.awssdk.services.sfn.model.StartExecutionRequest;
import software.amazon.awssdk.services.sfn.model.StartExecutionResponse;

@Service
public class StepFunctionService {

    private final SfnClient sfnClient;
    private final String stateMachineArn;

    public StepFunctionService(@Value("${aws.state-machine-arn}") String stateMachineArn, 
                               @Value("${aws.region}") String region) {
        this.sfnClient = SfnClient.builder()
                .region(Region.of(region))
                .build();
        this.stateMachineArn = stateMachineArn;
    }

    public String startOrderProcessing(Map<String, Object> inputData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String inputJson = objectMapper.writeValueAsString(inputData); // ✅ Proper JSON format

            StartExecutionRequest request = StartExecutionRequest.builder()
                    .stateMachineArn(stateMachineArn)
                    .input(inputJson)
                    .build();

            StartExecutionResponse response = sfnClient.startExecution(request);
            return response.executionArn();
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize input to JSON", e);
        }
    }
    
 }
    
    
    
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    public Map<String, Object> startOrderProcessing(Map<String, Object> inputData) {
//        try {
//            // Convert input map to JSON string
//            String inputJson = objectMapper.writeValueAsString(inputData);
//
//            // Start Execution
//            StartExecutionRequest request = StartExecutionRequest.builder()
//                    .stateMachineArn(stateMachineArn)
//                    .input(inputJson)
//                    .build();
//
//            StartExecutionResponse response = sfnClient.startExecution(request);
//            String executionArn = response.executionArn();
//
//            // Wait for execution to complete
//            DescribeExecutionRequest describeRequest = DescribeExecutionRequest.builder()
//                    .executionArn(executionArn)
//                    .build();
//
//            DescribeExecutionResponse executionResponse;
//            do {
//                Thread.sleep(2000); // Wait for 2 seconds before polling
//                executionResponse = sfnClient.describeExecution(describeRequest);
//            } while (executionResponse.status() == ExecutionStatus.RUNNING);
//
//            // Parse output JSON
//            JsonNode outputJson = objectMapper.readTree(executionResponse.output());
//
//            return objectMapper.convertValue(outputJson, Map.class);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to process order", e);
//        }
//    }
//}
