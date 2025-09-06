package com.nt.model;

import lombok.Data;

@Data
public class PredictRequest {
	private String encryptedInput;
	private String zkpProof;

}
