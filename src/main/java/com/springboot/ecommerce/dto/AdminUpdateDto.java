package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.util.ValidationConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpdateDto {

	@NotBlank(message = ValidationConstants.USER_NAME_REQUIRED)
    @Size(min = 3, max = 20, message =ValidationConstants.NAME_SIZE )
    @Pattern(regexp = ValidationConstants.PERSON_NAME_REGEX, message = ValidationConstants.INVALID_NAME)
    private String name;
    
    @NotBlank(message = ValidationConstants.MOBILE_REQUIRED)
    @Pattern(regexp = ValidationConstants.MOBILE_REGEX, message = ValidationConstants.INVALID_MOBILE)
    private String mobileNo;
    
    @NotBlank(message = ValidationConstants.PASSWORD_REQUIRED)
    @Size(min = 8, max = 20, message = ValidationConstants.PASSWORD_SIZE)
    @Pattern(regexp = ValidationConstants.PASSWORD_REGEX, message = ValidationConstants.INVALID_PASSWORD)
    private String password;

    @NotBlank(message = ValidationConstants.CONFIRM_PASSWORD_REQUIRED)
    @Size(min = 8, max = 20, message = ValidationConstants.PASSWORD_SIZE)
    private String confirmPassword;
}
