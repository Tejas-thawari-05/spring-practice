package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.entity.Employee;
import com.repository.Emp_Repository;

@Component
public class CustomeUserDetailService implements UserDetailsService{

	@Autowired
	private Emp_Repository empRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = empRepository.findByEmail(username);
		if(emp == null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			return new Custome_User(emp);
		}
	}

	

}
