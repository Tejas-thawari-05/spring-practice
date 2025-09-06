package com.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.service.PqcCryptoService;

@RestController
@RequestMapping("/api/pqc")
public class PqcController {
    private final PqcCryptoService pqcCryptoService;

    public PqcController(PqcCryptoService pqcCryptoService) {
        this.pqcCryptoService = pqcCryptoService;
    }

    @PostMapping("/encrypt")
    public Map<String, String> encrypt(@RequestBody Map<String, String> request) throws Exception {
        String encryptedMessage = pqcCryptoService.encryptMessage(request.get("message"));
        return Map.of("encryptedMessage", encryptedMessage);
    }

    @PostMapping("/decrypt")
    public Map<String, String> decrypt(@RequestBody Map<String, String> request) throws Exception {
        String decryptedMessage = pqcCryptoService.decryptMessage(request.get("encryptedMessage"));
        return Map.of("message", decryptedMessage);
    }

    @PostMapping("/sign")
    public Map<String, String> sign(@RequestBody Map<String, String> request) {
        String signature = pqcCryptoService.signMessage(request.get("message"));
        return Map.of("signature", signature);
    }

    @PostMapping("/verify")
    public Map<String, Boolean> verify(@RequestBody Map<String, String> request) {
        boolean isValid = pqcCryptoService.verifySignature(request.get("message"), request.get("signature"));
        return Map.of("valid", isValid);
    }
}
