package com.rntity;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyPair {

    @JsonIgnore
    private byte[] publicKey;
    
    @JsonIgnore
    private byte[] privateKey;

    public KeyPair(byte[] publicKey, byte[] privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @JsonProperty("publicKey")
    public String getPublicKeyBase64() {
        return Base64.getEncoder().encodeToString(publicKey);
    }

    @JsonProperty("privateKey")
    public String getPrivateKeyBase64() {
        return Base64.getEncoder().encodeToString(privateKey);
    }

    @JsonProperty("publicKey")
    public void setPublicKeyBase64(String publicKeyBase64) {
        this.publicKey = Base64.getDecoder().decode(publicKeyBase64);
    }

    @JsonProperty("privateKey")
    public void setPrivateKeyBase64(String privateKeyBase64) {
        try {
            this.privateKey = Base64.getDecoder().decode(privateKeyBase64); // Standard Base64 decoder
        } catch (IllegalArgumentException e) {
            // Try URL-safe decoder if standard fails
            this.privateKey = Base64.getUrlDecoder().decode(privateKeyBase64);
        }
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }
    


   
}
