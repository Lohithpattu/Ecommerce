package com.springboot.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.dto.AdminRegisterDto;
import com.springboot.ecommerce.dto.AdminUpdateDto;
import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.service.AdminService;
import com.springboot.ecommerce.util.ApiResponse;

@RestController
@RequestMapping("/ecom/admins")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerAdmin(@Validated @RequestBody AdminRegisterDto dto)
	{
		 ApiResponse response = adminService.registerAdmin(dto);
		 
		 return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginDto dto)
	{
		ApiResponse response = adminService.loginUser(dto);
		
	    return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping("update/{email}")
	public ResponseEntity<ApiResponse> updateAdmin(@PathVariable String email, @Validated @RequestBody AdminUpdateDto dto)
	{
		ApiResponse response = adminService.updateAdmin(email, dto);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PatchMapping("update/{email}")
	public ResponseEntity<ApiResponse> patchAdmin(@PathVariable String email, @RequestBody Map<String, Object> updates)
	{
		ApiResponse response = adminService.patchAdmin(email, updates);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable String email)
	{
		ApiResponse response = adminService.deleteAdmin(email);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/fetchall")
	public ResponseEntity<ApiResponse> fetchAllAdmins()
	{
		ApiResponse response = adminService.fetchAllAdmins();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.FOUND);
	}

}
