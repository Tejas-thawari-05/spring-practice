package com.service;

import java.util.List;

import com.DTO.UserRequest;
import com.model.UserDtls;

public interface User_Service {

	public String login(UserRequest request);
	
	public List<UserDtls> getUserDetails();
}
