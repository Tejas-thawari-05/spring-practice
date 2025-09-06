package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageProcessingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageProcessingServiceApplication.class, args);
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Secure 512-bit key
//		String base64Key = Encoders.BASE64.encode(key.getEncoded());
//		
//		 System.out.println(base64Key);
//	        System.out.println(key);
	}

}
