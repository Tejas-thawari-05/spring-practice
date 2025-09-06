package com.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entity.Employee;

public class Custome_User implements UserDetails{

	private Employee emp;
	
	public Custome_User(Employee emp) {
		super();
		this.emp = emp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(emp.getRole());
		
		return Arrays.asList(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		
		return emp.getPassword();
	}

	@Override
	public String getUsername() {
		return emp.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
