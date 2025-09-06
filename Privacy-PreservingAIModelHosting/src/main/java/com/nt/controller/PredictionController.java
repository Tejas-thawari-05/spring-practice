package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.PredictRequest;
import com.nt.security.ZKPVerifier;
import com.nt.service.EncryptionService;
import com.nt.service.ModelService;

@RestController
@RequestMapping("/api")
public class PredictionController {

    @Autowired
    private ModelService modelService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private ZKPVerifier zkpVerifier;

    @PostMapping("/predict")
    public String predictSecurely(@RequestBody PredictRequest request) {
        if (!zkpVerifier.verifyProof(request.getZkpProof())) {
            return "Access denied: Invalid ZKP proof.";
        }

        double input = encryptionService.homomorphicDecrypt(request.getEncryptedInput());
        double result = modelService.predict(input);
        return "Encrypted prediction result: " + encryptionService.homomorphicEncrypt(result);
    }
}
