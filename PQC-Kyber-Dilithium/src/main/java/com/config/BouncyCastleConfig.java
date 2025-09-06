package com.config;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class BouncyCastleConfig {

    @PostConstruct
    public void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }
}
