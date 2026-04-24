package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.dto.UserRegisterDto;
import com.springboot.ecommerce.dto.UserResponseDto;

public interface UserService {

    Long registerUser(UserRegisterDto dto);

    Long loginUser(LoginDto dto);

    Long patchUser(String email, Map<String, Object> updates);

    Long deactivateUser(String email);

    List<UserResponseDto> fetchAllActiveCustomerUsers();
}
