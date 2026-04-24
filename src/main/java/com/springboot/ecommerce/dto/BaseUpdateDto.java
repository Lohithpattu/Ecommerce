
package com.springboot.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseUpdateDto {

    // Identity (Optional)
    private String name;

    // Contact (Optional)
    private String mobileNo;

    // Security (Optional)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;
}