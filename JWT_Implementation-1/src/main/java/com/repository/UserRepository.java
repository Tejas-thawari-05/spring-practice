package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer>{

	public UserDtls findByUsername(String username);
}
