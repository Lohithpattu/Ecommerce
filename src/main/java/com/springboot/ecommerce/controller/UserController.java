package com.springboot.ecommerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.dto.UserRegisterDto;
import com.springboot.ecommerce.dto.UserResponseDto;
import com.springboot.ecommerce.service.UserService;
import com.springboot.ecommerce.util.ApiResponse;

@RestController
@RequestMapping("/ecom/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Long>> registerUser(@Validated @RequestBody UserRegisterDto dto) {

        Long userId = userService.registerUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created successfully", userId));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Long>> login(@RequestBody LoginDto dto) {

        Long userId = userService.loginUser(dto);

        return ResponseEntity.ok(
                ApiResponse.success("Login successful", userId)
        );
    }

    @PatchMapping("/{email}")
    public ResponseEntity<ApiResponse<Long>> patchUser(@PathVariable String email,
                                                 @RequestBody Map<String, Object> updates) {

        Long userId = userService.patchUser(email, updates);

        return ResponseEntity.ok(
                ApiResponse.success("User updated successfully", userId)
        );
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<ApiResponse<Long>> deactivateUser(@PathVariable String email) {

        Long userId = userService.deactivateUser(email);

        return ResponseEntity.ok(
                ApiResponse.success("User deactivated successfully", userId)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> fetchAllActiveCustomerUsers() {

        List<UserResponseDto> users = userService.fetchAllActiveCustomerUsers();

        return ResponseEntity.ok(
                ApiResponse.success("Users fetched successfully", users)
        );
    }
}
