//package com.example.UserMigrationService;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class WelcomeController {
//    private final FeatureToggleService toggleService;
//
//    public WelcomeController(FeatureToggleService toggleService) {
//        this.toggleService = toggleService;
//    }
//
//    @GetMapping("/welcome")
//    public String welcome() {
//        if (toggleService.isEnabled("new-feature")) {
//            return "Welcome to the NEW feature!";
//        }
//        return "Welcome to the classic feature.";
//    }
//}
