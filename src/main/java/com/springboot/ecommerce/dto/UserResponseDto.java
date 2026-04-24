package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.enums.AccountStatus;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String email;

    private String mobileNo;

    private AccountStatus accountStatus;
}