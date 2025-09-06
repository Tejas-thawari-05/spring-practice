package com.service;



import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BlockchainService {
    private final Map<String, String> postStorage = new ConcurrentHashMap<>();

    public void saveToBlockchain(String user, String encryptedContent) {
        postStorage.put(user + System.currentTimeMillis(), encryptedContent);
    }

    public String getEncryptedContent(String postId) {
        return postStorage.get(postId);
    }

    public Map<String, String> getAll() {
        return postStorage;
    }
}
