package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.util.ValidationConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

	@NotBlank(message = ValidationConstants.EMAIL_REQUIRED)
    @Email(message = ValidationConstants.INVALID_EMAIL)
    @Size(max = 50, message = ValidationConstants.EMAIL_SIZE)
    private String email;

    @NotBlank(message = ValidationConstants.PASSWORD_REQUIRED)
    @Size(min = 8, max = 20, message = ValidationConstants.PASSWORD_SIZE)
    @Pattern(regexp = ValidationConstants.PASSWORD_REGEX, message = ValidationConstants.INVALID_PASSWORD)
    private String password;
}
