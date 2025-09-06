package com.nt.service;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    public String homomorphicEncrypt(double input) {
        return "ENC_" + input;
    }

    public double homomorphicDecrypt(String encrypted) {
        return Double.parseDouble(encrypted.replace("ENC_", ""));
    }
}
