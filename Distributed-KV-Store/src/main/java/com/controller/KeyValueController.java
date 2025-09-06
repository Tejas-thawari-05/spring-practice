package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.model.KeyValue;
import com.service.KeyValueService;
import com.service.LeaderElectionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kv")
@RequiredArgsConstructor
public class KeyValueController {

    private final KeyValueService keyValueService;
    private final LeaderElectionService leaderService;

    @Value("${leader.url:http://localhost:8080}")
    private String leaderUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity<String> put(@RequestBody KeyValue kv) {
        if (!leaderService.isLeader()) {
            // Instead of redirecting, return error with leader info
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("post and put are only allowed on the leader node. Please use: " + leaderUrl);
        }

        keyValueService.put(kv.getKey(), kv.getValue());
        return ResponseEntity.ok("Stored by Leader");
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> get(@PathVariable String key) {
        if (leaderService.isLeader()) {
            return keyValueService.get(key)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(leaderUrl + "/kv/" + key, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Leader unavailable");
        }
    }
}
