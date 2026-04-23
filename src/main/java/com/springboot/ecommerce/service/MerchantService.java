package com.springboot.ecommerce.service;

import java.util.Map;

import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.dto.MerchantRegisterDto;
import com.springboot.ecommerce.dto.MerchantUpdateDto;
import com.springboot.ecommerce.util.ApiResponse;

public interface MerchantService {

	ApiResponse registerMerchant(MerchantRegisterDto dto);
	
	ApiResponse loginUser(LoginDto dto);
	
	ApiResponse updateMerchant(String email, MerchantUpdateDto dto);
	
	ApiResponse patchMerchant(String email, Map<String, Object> updates);
	
	ApiResponse deleteMerchant(String email);
	
	ApiResponse fetchAllMerchants();
}
