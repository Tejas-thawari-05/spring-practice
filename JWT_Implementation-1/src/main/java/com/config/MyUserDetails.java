package com.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.UserDtls;


public class MyUserDetails implements UserDetails{

	private UserDtls userDtls;
	
	
	public UserDtls getUserDtls() {
		return userDtls;
	}

	public void setUserDtls(UserDtls userDtls) {
		this.userDtls = userDtls;
	}

	public MyUserDetails(UserDtls userDtls) {
		super();
		this.userDtls = userDtls;
	}

	public MyUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return userDtls.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userDtls.getUsername();
	}

}
