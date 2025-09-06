package com.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/identity")
public class DIDController {
    
	@PostMapping("/verify")
    public Map<String, Object> verify(@RequestBody Map<String, String> payload) {
        // Mock DID verification for now
        String address = payload.get("address");
        return Map.of("verified", address != null && address.startsWith("0x"));
    }

}



