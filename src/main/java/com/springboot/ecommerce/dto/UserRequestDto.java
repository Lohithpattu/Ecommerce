package com.springboot.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$")
    private String user_name;

    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String mobile_no;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$")
    private String password;

    @NotBlank
    private String confirm_password;

    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordMatching() {
        return password != null && password.equals(confirm_password);
    }
}