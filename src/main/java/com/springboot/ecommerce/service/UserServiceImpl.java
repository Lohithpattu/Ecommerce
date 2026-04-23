package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.dto.UserRegisterDto;
import com.springboot.ecommerce.dto.UserUpdateDto;
import com.springboot.ecommerce.entity.Account;
import com.springboot.ecommerce.entity.User;
import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.exceptionhandling.AccountNotFoundException;
import com.springboot.ecommerce.exceptionhandling.DuplicateAccountException;
import com.springboot.ecommerce.exceptionhandling.InvalidCredentialsException;
import com.springboot.ecommerce.exceptionhandling.PasswordMismatchException;
import com.springboot.ecommerce.repository.UserRepository;
import com.springboot.ecommerce.util.ApiResponse;
import com.springboot.ecommerce.util.AppConstants;

import tools.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	private void validateEmail(String email)
	{
		if(userRepository.findByAccount_Email(email).isPresent())
		{
			throw new DuplicateAccountException(AppConstants.EMAIL_ALREADY_EXISTS);
		}
		
	}
	
	@Transactional
	public ApiResponse registerUser(UserRegisterDto dto)
	{
		if (!dto.getPassword().equals(dto.getConfirmPassword())) 
		{
            throw new PasswordMismatchException(AppConstants.PASSWORD_MISMATCH);
        }
		
		validateEmail(dto.getEmail());
		
		Account account = new Account();
		
		account.setRole(dto.getRole());
		account.setMobileNo(dto.getMobileNo());
		account.setEmail(dto.getEmail());
		account.setPassword(dto.getPassword());
		
		
		
		User user = new User();
		
		user.setName(dto.getName());
		
		user.setAccount(account);
		account.setUser(user);
		
		userRepository.save(user);
		
		return new ApiResponse("User created successfully", 201, null);
			
	}
	
	@Transactional(readOnly = true)
	public ApiResponse loginUser(LoginDto dto)
	{
		 User user = userRepository.findByAccount_Email(dto.getEmail())
		            .orElseThrow(() -> new InvalidCredentialsException(AppConstants.INVALID_CREDENTIALS));

		    if(!dto.getPassword().equals(user.getAccount().getPassword()))
		    {
		        throw new InvalidCredentialsException(AppConstants.INVALID_CREDENTIALS);
		    }

		    return new ApiResponse("Login successful", 200, null);
	}
	
	@Transactional
	public ApiResponse updateUser(String email, UserUpdateDto dto)
	{
		User user = userRepository.findByAccount_Email(email)
	            .orElseThrow(() -> new AccountNotFoundException(AppConstants.ACCOUNT_NOT_FOUND));
		
		Account account = user.getAccount();
		
		if(dto.getName() != null)
		{
			user.setName(email);
		}
		
		if (dto.getMobileNo() != null)
		{
			account.setMobileNo(dto.getMobileNo());
		}
		
		if(dto.getPassword() != null)
		{
			if(!dto.getPassword().equals(dto.getConfirmPassword()))
			{
				throw new PasswordMismatchException(AppConstants.PASSWORD_MISMATCH);
			}
			
			account.setPassword(dto.getPassword());
		}
		
		userRepository.save(user);
		
		return new ApiResponse("User account updated successfully",200,null);
		
	}
	
	@Transactional
	public ApiResponse patchUser(String email, Map<String, Object> updates)
	{
		User user = userRepository.findByAccount_Email(email)
	            .orElseThrow(() -> new AccountNotFoundException(AppConstants.ACCOUNT_NOT_FOUND));
		
		Account account = user.getAccount();
		
		//  Convert Map → DTO
		ObjectMapper mapper = new ObjectMapper();
		UserUpdateDto dto = mapper.convertValue(updates, UserUpdateDto.class);
		
		if(dto.getName() != null)
		{
			user.setName(email);
		}
		
		if (dto.getMobileNo() != null)
		{
			account.setMobileNo(dto.getMobileNo());
		}
		
		if(dto.getPassword() != null)
		{
			if(!dto.getPassword().equals(dto.getConfirmPassword()))
			{
				throw new PasswordMismatchException(AppConstants.PASSWORD_MISMATCH);
			}
			
			account.setPassword(dto.getPassword());
		}
		
		userRepository.save(user);
		
		return new ApiResponse("User account patched successfully",200,null);
		
	}
	
	
	@Transactional
	public ApiResponse deleteUser(String email) 
	{
		User user = userRepository.findByAccount_Email(email)
	            .orElseThrow(() -> new AccountNotFoundException(AppConstants.ACCOUNT_NOT_FOUND));
		
		userRepository.delete(user);
		
		return new ApiResponse("User deleted successfully", 200, null);
	}
	
	
	@Transactional
	public ApiResponse fetchAllUsers()
	{
		List<User> users = userRepository.findByAccount_Role(Role.USER);
		
		if(users.isEmpty())
		{
			return new ApiResponse("No users found",404,null);
		}
		
		return new ApiResponse("Users fetched successfully",302,users);		
	}

}
