package com.controller;

import org.springframework.web.bind.annotation.*;

import com.seervice.CryptoService;

import java.util.Map;

@RestController
@RequestMapping("/api/pqc")
public class CryptoController {
    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @PostMapping("/encrypt")
    public Map<String, String> encrypt(@RequestBody Map<String, String> request) throws Exception {
        String encryptedMessage = cryptoService.encryptMessage(request.get("message"));
        return Map.of("encryptedMessage", encryptedMessage);
    }

    @PostMapping("/sign")
    public Map<String, String> sign(@RequestBody Map<String, String> request) {
        String signature = cryptoService.generateSignature(request.get("message"));
        return Map.of("signature", signature);
    }
}