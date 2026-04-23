package com.springboot.ecommerce.service;

import java.util.Map;

import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.dto.UserRegisterDto;
import com.springboot.ecommerce.dto.UserUpdateDto;
import com.springboot.ecommerce.util.ApiResponse;

public interface UserService {

	ApiResponse registerUser(UserRegisterDto dto);
	
	ApiResponse loginUser(LoginDto dto);
	
	ApiResponse updateUser(String email, UserUpdateDto dto);
	
	ApiResponse patchUser(String email, Map<String, Object> updates);
	
	ApiResponse deleteUser(String email);
	
	ApiResponse fetchAllUsers();
}
