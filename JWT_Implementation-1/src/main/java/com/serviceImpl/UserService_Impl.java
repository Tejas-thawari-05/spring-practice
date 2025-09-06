package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.DTO.UserRequest;
import com.model.UserDtls;
import com.repository.UserRepository;
import com.service.JWT_Service;
import com.service.User_Service;

@Service
public class UserService_Impl implements User_Service{

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWT_Service jwt_Service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String login(UserRequest userRequest) {
		Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword()));
		
		if(authenticate.isAuthenticated()) {
			//   return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		
			return jwt_Service.generateToken(userRequest.getUsername());
		
		}
		
		return "";
	}

	@Override
	public List<UserDtls> getUserDetails() {
		 return userRepository.findAll();
		
	}

}
