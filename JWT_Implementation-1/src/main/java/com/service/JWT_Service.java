package com.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWT_Service {

	private String secretKey = "";
	
	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", username);
		
		String token = Jwts.builder()
			.addClaims(claims)
			.setSubject(username)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 5     * 60 * 1000))
			.signWith(getkey())
			.compact();
		
		return token;
		
	}

	private Key getkey() {
		
		try {
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
			
			SecretKey secKey = keyGenerator.generateKey();
			
			secretKey = Base64.getEncoder().encodeToString(secKey.getEncoded());
			
			//  System.out.println(secretKey);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	
	private Claims extractAllClaims(String token) {
		
		
		// for decryption of the key
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		 SecretKey hmacShaKeyFor = Keys.hmacShaKeyFor(keyBytes);
		
		 return Jwts.parser().setSigningKey(hmacShaKeyFor).parseClaimsJws(token).getBody();
		
		 
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUserName(token);
		Boolean isExpired = isTokenExpired(token);
		
		if(username.equals(userDetails.getUsername())  &&  !isExpired) {
			return true;
		}
		return false;
	}

	private Boolean isTokenExpired(String token) {
		Date expiredDate = extractExpiration(token);
		return expiredDate.before(new Date());
	}
}
