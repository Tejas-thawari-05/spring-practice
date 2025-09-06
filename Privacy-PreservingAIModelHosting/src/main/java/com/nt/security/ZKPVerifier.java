package com.nt.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZKPVerifier {

    @Value("${secureai.zkp.valid-proof}")
    private String validProof;

    public boolean verifyProof(String proof) {
        return validProof.equals(proof);
    }
}
