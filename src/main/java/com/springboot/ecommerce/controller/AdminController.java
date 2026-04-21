package com.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.dto.AdminRequestDto;
import com.springboot.ecommerce.service.AdminService;

@RestController
@RequestMapping("/ecom/admins")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/register")
	public String registerAdmin(@Validated @RequestBody AdminRequestDto adminRequestDto)
	{
		return adminService.registerAdmin(adminRequestDto);
	}

}
