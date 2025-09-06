//package com.example.UserMigrationService;
//
//import io.getunleash.DefaultUnleash;
//import io.getunleash.Unleash;
//import io.getunleash.util.UnleashConfig;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FeatureToggleService {
//    private final Unleash unleash;
//
//    public FeatureToggleService() {
//        UnleashConfig config = UnleashConfig.builder()
//                .appName("user-service")
//                .instanceId("v1")
//                .unleashAPI("http://localhost:4242/api")
//                .build();
//        this.unleash = new DefaultUnleash(config);
//    }
//
//    public boolean isEnabled(String flagName) {
//        return unleash.isEnabled(flagName);
//    }
//}
//