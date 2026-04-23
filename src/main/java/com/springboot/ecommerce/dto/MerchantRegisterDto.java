package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.enums.Category;
import com.springboot.ecommerce.util.ValidationConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MerchantRegisterDto extends BaseRegisterDto{
	
    @NotBlank(message = ValidationConstants.BUSINESS_NAME_REQUIRED)
    @Size(min = 2, max = 200, message = ValidationConstants.NAME_SIZE)
    @Pattern(regexp = ValidationConstants.BUSINESS_NAME_REGEX, message = ValidationConstants.INVALID_BUSINESS_NAME)
    private String businessName;

    @NotNull(message = ValidationConstants.CATEGORY_REQUIRED)
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

}
