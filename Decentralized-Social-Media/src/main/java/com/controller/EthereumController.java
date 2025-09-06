package com.controller;

import com.service.EthereumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class EthereumController {

    @Autowired
    private EthereumService ethereumService;

    @PostMapping
    public Map<String, Object> createPost(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String content = request.get("content");
            String txHash = ethereumService.createPost(content);
            response.put("status", "Post created");
            response.put("transactionHash", txHash);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @GetMapping("/count")
    public Map<String, Object> getPostCount() {
        Map<String, Object> response = new HashMap<>();
        try {
            BigInteger count = ethereumService.getPostCount();
            response.put("count", count);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @GetMapping("/{index}")
    public Map<String, Object> getPost(@PathVariable BigInteger index) {
        Map<String, Object> response = new HashMap<>();
        try {
            Tuple2<String, String> post = ethereumService.getPost(index);
            response.put("content", post.getValue1());
            response.put("author", post.getValue2());
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }
}
