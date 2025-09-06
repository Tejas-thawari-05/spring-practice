package com.controller;




import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.model.PostDto;
import com.service.BlockchainService;
import com.util.AesEncryptionUtil;

import java.util.Map;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final AesEncryptionUtil aes;
    private final BlockchainService blockchain;

    @PostMapping
    public String post(@RequestBody PostDto post) throws Exception {
        String encrypted = aes.encrypt(post.getContent());
        blockchain.saveToBlockchain(post.getUserAddress(), encrypted);
        return "Post successfully encrypted and saved on blockchain";
    }

    @GetMapping
    public Map<String, String> all() {
        return blockchain.getAll();
    }

    @GetMapping("/decrypt/{postId}")
    public String decrypt(@PathVariable String postId) throws Exception {
        String enc = blockchain.getEncryptedContent(postId);
        return aes.decrypt(enc);
    }
}
