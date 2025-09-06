package com.nt.service;

import org.springframework.stereotype.Service;

@Service
public class ModelService {

	public double predict(double input) {
		// Simulate model call (replace with real TF Serving REST API later)
		return input * 2 + 1; // y = 2x + 1 model simulation
	}

}
