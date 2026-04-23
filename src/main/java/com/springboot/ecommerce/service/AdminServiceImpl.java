package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ecommerce.dto.AdminRegisterDto;
import com.springboot.ecommerce.dto.AdminUpdateDto;
import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.entity.Account;
import com.springboot.ecommerce.entity.Admin;
import com.springboot.ecommerce.entity.User;
import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.exceptionhandling.AccountNotFoundException;
import com.springboot.ecommerce.exceptionhandling.DuplicateAccountException;
import com.springboot.ecommerce.exceptionhandling.InvalidCredentialsException;
import com.springboot.ecommerce.exceptionhandling.PasswordMismatchException;
import com.springboot.ecommerce.repository.AdminRepository;
import com.springboot.ecommerce.util.ApiResponse;
import com.springboot.ecommerce.util.AppConstants;

import tools.jackson.databind.ObjectMapper;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminRepository adminRepository;
	
	private void validateEmail(String email)
	{
		if(adminRepository.findByAccount_Email(email).isPresent())
		{
			throw new DuplicateAccountException(AppConstants.EMAIL_ALREADY_EXISTS);
		}	
	}
	
	@Transactional
	public ApiResponse registerAdmin(AdminRegisterDto dto)
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
		
		Admin admin = new Admin();
		
		admin.setName(dto.getName());
		
		admin.setAccount(account);
		account.setAdmin(admin);
		
		adminRepository.save(admin);
		
		return new ApiResponse("Admin created successfully", 201, null);
			
	}
	
	
	@Transactional(readOnly = true)
	public ApiResponse loginUser(LoginDto dto)
	{
		 Admin admin = adminRepository.findByAccount_Email(dto.getEmail())
		            .orElseThrow(() -> new InvalidCredentialsException(AppConstants.INVALID_CREDENTIALS));

		    if(!dto.getPassword().equals(admin.getAccount().getPassword()))
		    {
		        throw new InvalidCredentialsException(AppConstants.INVALID_CREDENTIALS);
		    }

		    return new ApiResponse("Login successful", 200, null);
	}
	
	@Transactional
	public ApiResponse updateAdmin(String email, AdminUpdateDto dto)
	{
		Admin admin = adminRepository.findByAccount_Email(email)
	            .orElseThrow(() -> new AccountNotFoundException(AppConstants.ACCOUNT_NOT_FOUND));
		
		Account account = admin.getAccount();
		
		if(dto.getName() != null)
		{
			admin.setName(email);
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
		
		adminRepository.save(admin);
		
		return new ApiResponse("Admin account updated successfully",200,null);
		
	}
	
	@Transactional
	public ApiResponse patchAdmin(String email, Map<String, Object> updates)
	{
		Admin admin = adminRepository.findByAccount_Email(email)
	            .orElseThrow(() -> new AccountNotFoundException(AppConstants.ACCOUNT_NOT_FOUND));
		
		Account account = admin.getAccount();
		
		//  Convert Map → DTO
		ObjectMapper mapper = new ObjectMapper();
		AdminUpdateDto dto = mapper.convertValue(updates, AdminUpdateDto.class);
		
		if(dto.getName() != null)
		{
			admin.setName(email);
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
		
		adminRepository.save(admin);
		
		return new ApiResponse("Admin account patched successfully",200,null);
		
	}
	
	
	@Transactional
	public ApiResponse deleteAdmin(String email) 
	{
		Admin admin = adminRepository.findByAccount_Email(email)
	            .orElseThrow(() -> new AccountNotFoundException(AppConstants.ACCOUNT_NOT_FOUND));
		
		adminRepository.delete(admin);
		
		return new ApiResponse("Admin deleted successfully", 200, null);
	}
	
	
	@Transactional
	public ApiResponse fetchAllAdmins()
	{
		List<Admin> admins = adminRepository.findByAccount_Role(Role.ADMIN);
		
		if(admins.isEmpty())
		{
			return new ApiResponse("No admins found",404,null);
		}
		
		return new ApiResponse("Admins fetched successfully",302,admins);		
	}
}
