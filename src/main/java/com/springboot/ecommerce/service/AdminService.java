package com.springboot.ecommerce.service;

import java.util.Map;

import com.springboot.ecommerce.dto.AdminRegisterDto;
import com.springboot.ecommerce.dto.AdminUpdateDto;
import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.util.ApiResponse;

public interface AdminService {

	ApiResponse registerAdmin(AdminRegisterDto dto);
	
	ApiResponse loginUser(LoginDto dto);
	
	ApiResponse updateAdmin(String email, AdminUpdateDto dto);
	
	ApiResponse patchAdmin(String email, Map<String, Object> updates);
	
	ApiResponse deleteAdmin(String email);
	
	ApiResponse fetchAllAdmins();
}
