package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.util.AesEncryptionUtil;

@Configuration
public class AppConfig {

    @Bean
    public AesEncryptionUtil aesEncryptionUtil() {
        return new AesEncryptionUtil();
    }
}