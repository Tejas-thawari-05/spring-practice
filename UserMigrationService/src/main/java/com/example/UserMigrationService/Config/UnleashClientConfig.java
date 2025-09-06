package com.example.UserMigrationService.Config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnleashClientConfig {

    @Bean
    public Unleash unleash() {
    	UnleashConfig config = UnleashConfig.builder()
    		    .appName("unleash-onboarding-java")
    		    .instanceId("unleash-onboarding-instance")
    		    .unleashAPI("https://eu.app.unleash-hosted.com/demo/api/")
    		    .apiKey("zerodowntime:development.b1bffd09504e83e9e656eef5d8cc6b1e66a1b2d6f0e2d68baa239afb") // in production use environment variable
    		    .build();

        return new DefaultUnleash(config);
    }
}