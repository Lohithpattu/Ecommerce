package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.enums.Category;
import com.springboot.ecommerce.util.ValidationConstants;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MerchantRegisterDto {
	
    @NotBlank(message = ValidationConstants.MERCHANT_NAME_REQUIRED)
    @Size(min = 3, max = 50, message = ValidationConstants.NAME_SIZE)
    @Pattern(regexp = ValidationConstants.PERSON_NAME_REGEX, message = ValidationConstants.INVALID_NAME)
    private String merchantName;

    @NotBlank(message = ValidationConstants.MOBILE_REQUIRED)
    @Pattern(regexp = ValidationConstants.MOBILE_REGEX, message = ValidationConstants.INVALID_MOBILE)
    private String mobileNo;

    @NotBlank(message = ValidationConstants.BUSINESS_NAME_REQUIRED)
    @Size(min = 2, max = 200, message = ValidationConstants.NAME_SIZE)
    @Pattern(regexp = ValidationConstants.BUSINESS_NAME_REGEX, message = ValidationConstants.INVALID_NAME)
    private String businessName;

    @NotNull(message = "Category is required")
    private Category category;
    
    @NotBlank(message = ValidationConstants.ADDRESS_REQUIRED)
    @Size(min = 3, max = 255, message = ValidationConstants.ADDRESS_SIZE)
    private String address;
    
    @NotBlank(message = ValidationConstants.GST_REQUIRED)
    @Size(min = 15, max = 15, message = ValidationConstants.GST_SIZE)
    @Pattern(regexp = ValidationConstants.GST_REGEX, message = ValidationConstants.INVALID_GST)
    private String gstNo;
    
    @Size(max = 255, message = ValidationConstants.DESCRIPTION_SIZE)
    private String storeDescription;

    @NotBlank(message = ValidationConstants.EMAIL_REQUIRED)
    @Email(message = ValidationConstants.INVALID_EMAIL)
    @Size(max = 50, message = ValidationConstants.EMAIL_SIZE)
    private String email;

    @NotBlank(message = ValidationConstants.PASSWORD_REQUIRED)
    @Size(min = 8, max = 20, message = ValidationConstants.PASSWORD_SIZE)
    @Pattern(regexp = ValidationConstants.PASSWORD_REGEX, message = ValidationConstants.INVALID_PASSWORD)
    private String password;

    @NotBlank(message = ValidationConstants.CONFIRM_PASSWORD_REQUIRED)
    private String confirmPassword;

    @AssertTrue(message = ValidationConstants.PASSWORD_MISMATCH)
    public boolean isPasswordMatching() {
        return 
        		password != null && 
        		confirmPassword != null && 
        		password.equals(confirmPassword);
    }
}
