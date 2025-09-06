package com.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}/c")
    @CircuitBreaker(name = "marksService", fallbackMethod = "fallbackMarksCircuitBreaker")
    public CompletableFuture<String> getStudentWithMarksCircuitBreaker(@PathVariable int id) {
        return CompletableFuture.supplyAsync(() -> {
            String studentInfo = "Student ID: " + id + " Name: tejas";
            String marks = restTemplate.getForObject("http://localhost:8081/marks/" + id, String.class);
            return studentInfo + " | Marks: " + marks;
        });
    }

    public CompletableFuture<String> fallbackMarksCircuitBreaker(int id, Throwable throwable) {
        return CompletableFuture.completedFuture("Student ID: " + id + " Name: tejas  | Marks: Service Unavailable" + " From CircuitBreaker ");
    }
    
//    @GetMapping("/{id}/b")
//    @Bulkhead(name = "marksService" , fallbackMethod = "fallbackMarksBulkHead")
//    public CompletableFuture<String> getStudentWithMarksBulkHead(@PathVariable int id) {
//        return CompletableFuture.supplyAsync(() -> {
//            String studentInfo = "Student ID: " + id + " Name: tejas";
//            String marks = restTemplate.getForObject("http://localhost:8081/marks/" + id, String.class);
//            return studentInfo + " | Marks: " + marks;
//        });
//    }
//    
//    public CompletableFuture<String> fallbackMarksBulkHead(int id, Throwable throwable) {
//        return CompletableFuture.completedFuture("Student ID: " + id + " Name: tejas  | Marks: Service Unavailable" + " From BulkHead ");
//    }
    
    
    @GetMapping("/{id}/b")
    @Bulkhead(name = "marksService", fallbackMethod = "fallbackMarksBulkHead")
    public CompletableFuture<String> getStudentWithMarksBulkHead(@PathVariable int id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            String studentInfo = "Student ID: " + id + " Name: Tejas";
            String marks = restTemplate.getForObject("http://localhost:8081/marks/" + id, String.class);
            return studentInfo + " | Marks: " + marks;
        });
    }

    public CompletableFuture<String> fallbackMarksBulkHead(int id, Throwable throwable) {
        return CompletableFuture.completedFuture("Student ID: " + id + " Name: Tejas | Marks: Service Unavailable (From Bulkhead)");
    }
    
    
    
}
