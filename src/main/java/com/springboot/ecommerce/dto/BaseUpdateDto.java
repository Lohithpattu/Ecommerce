package com.springboot.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.ecommerce.util.ValidationConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class BaseUpdateDto {
	
			// Identity
	
		 	@NotBlank(message = ValidationConstants.NAME_REQUIRED)
		    @Size(min = 3, max = 50, message = ValidationConstants.NAME_SIZE)
		    @Pattern(regexp = ValidationConstants.PERSON_NAME_REGEX, message = ValidationConstants.INVALID_NAME)
		    private String name;
		 	
		 	// Contact Details
		 	
		    @NotBlank(message = ValidationConstants.MOBILE_REQUIRED)
		    @Pattern(regexp = ValidationConstants.MOBILE_REGEX, message = ValidationConstants.INVALID_MOBILE)
		    private String mobileNo;
		    
		    // Security
		    
		    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
		    @NotBlank(message = ValidationConstants.PASSWORD_REQUIRED)
		    @Size(min = 8, max = 20, message = ValidationConstants.PASSWORD_SIZE)
		    @Pattern(regexp = ValidationConstants.PASSWORD_REGEX, message = ValidationConstants.INVALID_PASSWORD)
		    private String password;
		    
		    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
		    @NotBlank(message = ValidationConstants.CONFIRM_PASSWORD_REQUIRED)
		    @Size(min = 8, max = 20, message = ValidationConstants.PASSWORD_SIZE)
		    private String confirmPassword;

}
