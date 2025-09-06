package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.repository.User_Repository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private User_Repository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
		
		return user;
	}

}
