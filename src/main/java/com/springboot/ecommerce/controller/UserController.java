package com.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.dto.LoginRequestDto;
import com.springboot.ecommerce.dto.UserRequestDto;
import com.springboot.ecommerce.service.UserService;
import com.springboot.ecommerce.util.ApiResponse;

@RestController
@RequestMapping("/ecom/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerUser(@Validated @RequestBody UserRequestDto dto)
	{
		 ApiResponse response = userService.registerUser(dto);
		 
		 return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> loginUser(@Validated @RequestBody LoginRequestDto dto) {

	    ApiResponse response = userService.loginUser(dto);

	    return ResponseEntity.ok(response);
	}

}
