package com.springboot.ecommerce.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ecommerce.dto.LoginRequestDto;
import com.springboot.ecommerce.dto.UserRequestDto;
import com.springboot.ecommerce.entity.User;
import com.springboot.ecommerce.exceptionhandling.DuplicateDataException;
import com.springboot.ecommerce.exceptionhandling.InvalidCredentialsException;
import com.springboot.ecommerce.exceptionhandling.PasswordMismatchException;
import com.springboot.ecommerce.repository.AdminRepository;
import com.springboot.ecommerce.repository.UserRepository;
import com.springboot.ecommerce.util.ApiResponse;
import com.springboot.ecommerce.util.AppConstants;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	private void validateEmail(String email)
	{
		if(userRepository.findByEmail(email).isPresent())
		{
			throw new DuplicateDataException(AppConstants.EMAIL_ALREADY_EXISTS);
		}
		
		if(adminRepository.findByEmail(email).isPresent())
		{
			throw new DuplicateDataException("This email belongs to Admin");
		}
	}
	
	@Transactional
	public ApiResponse registerUser(UserRequestDto dto)
	{
		if (!dto.getPassword().equals(dto.getConfirm_password())) 
		{
            throw new PasswordMismatchException(AppConstants.PASSWORD_MISMATCH);
        }
		
		validateEmail(dto.getEmail());
		
		User user = modelMapper.map(dto, User.class);
		
		String encryptedPassword = passwordEncoder.encode(dto.getPassword());
		
		user.setPassword(encryptedPassword);
		
		userRepository.save(user);
		
		return new ApiResponse("User created successfully", 201, null);
			
	}
	
	
	@Transactional
	public ApiResponse loginUser(LoginRequestDto dto)
	{
		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException(AppConstants.INVALID_CREDENTIALS));;
		
		boolean isMatch = passwordEncoder.matches(
				dto.getEmail(), 
				user.getEmail()
				);
		
		if(!isMatch) {
			throw new InvalidCredentialsException(AppConstants.INVALID_CREDENTIALS);
		}
		
		return new ApiResponse("Login Successful", 200, null);
	}
	

}
