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

import com.springboot.ecommerce.dto.UserRegisterDto;
import com.springboot.ecommerce.dto.UserUpdateDto;
import com.springboot.ecommerce.service.UserService;
import com.springboot.ecommerce.util.ApiResponse;

@RestController
@RequestMapping("/ecom/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerUser(@Validated @RequestBody UserRegisterDto dto)
	{
		 ApiResponse response = userService.registerUser(dto);
		 
		 return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("update/{email}")
	public ResponseEntity<ApiResponse> updateUser(@PathVariable String email, @Validated @RequestBody UserUpdateDto dto)
	{
		ApiResponse response = userService.updateUser(email, dto);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PatchMapping("update/{email}")
	public ResponseEntity<ApiResponse> patchUser(@PathVariable String email, @RequestBody Map<String, Object> updates)
	{
		ApiResponse response = userService.patchUser(email, updates);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String email)
	{
		ApiResponse response = userService.deleteUser(email);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/fetchall")
	public ResponseEntity<ApiResponse> fetchAllUsers()
	{
		ApiResponse response = userService.fetchAllUsers();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.FOUND);
	}

}
