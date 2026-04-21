package com.springboot.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.dto.AdminRequestDto;
import com.springboot.ecommerce.entity.Admin;
import com.springboot.ecommerce.exceptionhandling.DuplicateDataException;
import com.springboot.ecommerce.exceptionhandling.PasswordMismatchException;
import com.springboot.ecommerce.repository.AdminRepository;
import com.springboot.ecommerce.repository.UserRepository;
import com.springboot.ecommerce.util.AppConstants;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private void validateEmail(String email)
	{
		if(adminRepository.findByEmail(email).isPresent())
		{
			throw new DuplicateDataException(AppConstants.EMAIL_ALREADY_EXISTS);
		}
		
		if(userRepository.findByEmail(email).isPresent())
		{
			throw new DuplicateDataException("This email belongs to User");
		}
	}
	
	public String registerAdmin(AdminRequestDto dto)
	{
		if(!dto.getPassword().equals(dto.getConfirm_password()))
		{
			throw new PasswordMismatchException(AppConstants.PASSWORD_MISMATCH);
		}

		validateEmail(dto.getEmail());
		
		Admin admin = new Admin();
		
		admin.setAdmin_name(dto.getAdmin_name());
		admin.setMobile_no(dto.getMobile_no());
		admin.setEmail(dto.getEmail());
		admin.setPassword(dto.getPassword());
		
		adminRepository.save(admin);
		
		return "Admin Account -> created successfully...";
	}

}
