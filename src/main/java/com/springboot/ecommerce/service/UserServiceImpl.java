package com.springboot.ecommerce.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.ecommerce.dto.LoginDto;
import com.springboot.ecommerce.dto.UserRegisterDto;
import com.springboot.ecommerce.dto.UserResponseDto;
import com.springboot.ecommerce.dto.UserUpdateDto;
import com.springboot.ecommerce.entity.Account;
import com.springboot.ecommerce.entity.User;
import com.springboot.ecommerce.enums.AccountStatus;
import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.exceptionhandling.AccountNotFoundException;
import com.springboot.ecommerce.exceptionhandling.DuplicateAccountException;
import com.springboot.ecommerce.exceptionhandling.InActiveAccountException;
import com.springboot.ecommerce.exceptionhandling.InvalidAccountStateException;
import com.springboot.ecommerce.exceptionhandling.InvalidCredentialsException;
import com.springboot.ecommerce.exceptionhandling.PasswordMismatchException;
import com.springboot.ecommerce.mapper.UserMapper;
import com.springboot.ecommerce.repository.UserRepository;
import com.springboot.ecommerce.util.AppConstants;
import com.springboot.ecommerce.util.Normalizer;
import com.springboot.ecommerce.util.ValidationConstants;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    private final UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository,
                           ObjectMapper mapper,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }
    
    
    @Override
    public Long registerUser(UserRegisterDto dto) {

        String email = Normalizer.normalizeEmail(dto.getEmail());
        String mobile = Normalizer.normalizeMobile(dto.getMobileNo());

        log.info("Register request received. Email: {}", email);

        checkDuplicateAccount(email, mobile);

        log.debug("Mapping DTO to entity. Email: {}", email);

        Account account = userMapper.toAccount(dto);
        User user = userMapper.toUser(account);

        log.debug("Persisting user. Email: {}", email);

        User savedUser = userRepository.save(user);

        log.info("User registered successfully. UserId: {}, Email: {}", 
                 savedUser.getId(), email);

        return savedUser.getId();
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Long loginUser(LoginDto dto) {

        String email = Normalizer.normalizeEmail(dto.getEmail());

        log.info("Login attempt. Email: {}", email);

        User user = userRepository.findByAccount_Email(email)
                .orElseThrow(() -> {
                    log.warn("Login failed - user not found. Email: {}", email);
                    return new InvalidCredentialsException(AppConstants.INVALID_CREDENTIALS);
                });

        if (!dto.getPassword().equals(user.getAccount().getPassword())) {
            log.warn("Login failed - invalid password. Email: {}", email);
            throw new InvalidCredentialsException(AppConstants.INVALID_CREDENTIALS);
        }

        AccountStatus status = user.getAccount().getAccountStatus();

        if (status != AccountStatus.ACTIVE) {
            log.warn("Login failed - inactive account. Email: {}, Status: {}", email, status);
            throw new InActiveAccountException(AppConstants.ACCOUNT_INACTIVE);
        }

        log.info("Login successful. UserId: {}, Email: {}", user.getId(), email);

        return user.getId();
    }
    
    
    @Override
    public Long patchUser(String email, Map<String, Object> updates) {

        String normalizedEmail = Normalizer.normalizeEmail(email);

        log.info("PATCH request. Email: {}, Fields: {}", normalizedEmail, updates.keySet());

        User user = findActiveUser(normalizedEmail);
        Account account = user.getAccount();

        validateAllowedFields(updates);

        UserUpdateDto dto = mapper.convertValue(updates, UserUpdateDto.class);

        boolean updated = false;

        if (updates.containsKey("name")) {
            log.debug("Updating name. Email: {}", normalizedEmail);

            String name = validateName(dto.getName());
            if (!name.equals(account.getName())) {
                account.setName(name);
                updated = true;
            }
        }

        if (updates.containsKey("mobileNo")) {
            log.debug("Updating mobile. Email: {}", normalizedEmail);

            String mobile = validateMobile(dto.getMobileNo(), account.getMobileNo());
            if (!mobile.equals(account.getMobileNo())) {
                account.setMobileNo(mobile);
                updated = true;
            }
        }

        if (updates.containsKey("password")) {
            log.debug("Updating password. Email: {}", normalizedEmail);

            String password = validatePassword(dto, updates);
            account.setPassword(password);
            updated = true;
        }

        if (updates.containsKey("confirmPassword") && !updates.containsKey("password")) {
            log.warn("Invalid PATCH request - confirmPassword without password. Email: {}", normalizedEmail);
            throw new IllegalArgumentException("Password is required when confirmPassword is provided");
        }

        if (!updated) {
            log.info("PATCH skipped - no changes detected. Email: {}", normalizedEmail);
            return user.getId();
        }

        userRepository.save(user);

        log.info("User updated successfully. UserId: {}, Email: {}", 
                 user.getId(), normalizedEmail);

        return user.getId();
    }
	
	
    @Override
    public Long deactivateUser(String email) {

        String normalizedEmail = Normalizer.normalizeEmail(email);

        log.info("Deactivation request. Email: {}", normalizedEmail);

        User user = findUser(normalizedEmail);
        Account account = user.getAccount();

        AccountStatus status = account.getAccountStatus();

        if (status == AccountStatus.DEACTIVATED) {
            log.info("User already deactivated. Email: {}", normalizedEmail);
            return user.getId();
        }

        if (status != AccountStatus.ACTIVE) {
            log.warn("Invalid deactivation attempt. Email: {}, Status: {}", normalizedEmail, status);
            throw new InvalidAccountStateException(AppConstants.ACCOUNT_CANNOT_BE_DEACTIVATED);
        }

        account.setAccountStatus(AccountStatus.DEACTIVATED);

        userRepository.save(user);

        log.info("User deactivated successfully. UserId: {}, Email: {}", 
                 user.getId(), normalizedEmail);

        return user.getId();
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> fetchAllActiveCustomerUsers() {

        List<User> users = userRepository.findByAccount_RoleAndAccount_AccountStatus(
                Role.USER, AccountStatus.ACTIVE);

        return users.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }
    
    
    // Helper Methods
    
    private User findUser(String email) {
        return userRepository.findByAccount_Email(email)
                .orElseThrow(() -> {
                    log.warn("User not found. Email: {}", email);
                    return new AccountNotFoundException(AppConstants.ACCOUNT_NOT_FOUND);
                });
    }
    
    
    private User findActiveUser(String email) {

        log.debug("Checking active status for user: {}", email);

        User user = findUser(email);

        AccountStatus status = user.getAccount().getAccountStatus();

        if (status != AccountStatus.ACTIVE) {
            log.warn("Inactive account access attempt. Email: {}, Status: {}", email, status);
            throw new InActiveAccountException(AppConstants.ACCOUNT_INACTIVE);
        }

        return user;
    }
    
    
    private void checkDuplicateAccount(String email, String mobile) {

        log.debug("Checking duplicate account. Email: {}, Mobile: {}", email, mobile);

        if (userRepository.existsByAccount_EmailOrAccount_MobileNo(email, mobile)) {
            log.warn("Duplicate account detected. Email: {}, Mobile: {}", email, mobile);
            throw new DuplicateAccountException(AppConstants.ACCOUNT_ALREADY_EXISTS);
        }
    }
    
    
    private void validateAllowedFields(Map<String, Object> updates) {

        Set<String> allowed = Set.of("name", "mobileNo", "password", "confirmPassword");

        log.debug("Validating PATCH fields: {}", updates.keySet());

        for (String key : updates.keySet()) {
            if (!allowed.contains(key)) {
                log.warn("Invalid PATCH field detected. Field: {}, Allowed: {}", key, allowed);
                throw new IllegalArgumentException("Invalid field: " + key);
            }
        }
    }
    
    
    private String validateName(String name) {

        if (name == null || name.isBlank() ||
            !name.matches(ValidationConstants.PERSON_NAME_REGEX)) {

            log.warn("Invalid name provided: {}", name);
            throw new IllegalArgumentException("Invalid name");
        }

        return name.trim();
    }
    
    
    private String validateMobile(String input, String current) {

        String mobile = Normalizer.normalizeMobile(input);

        if (mobile.isBlank() ||
            !mobile.matches(ValidationConstants.MOBILE_REGEX)) {

            log.warn("Invalid mobile format. Input: {}", input);
            throw new IllegalArgumentException("Invalid mobile");
        }

        if (!mobile.equals(current) &&
            userRepository.existsByAccount_MobileNo(mobile)) {

            log.warn("Duplicate mobile detected: {}", mobile);
            throw new DuplicateAccountException(AppConstants.MOBILE_NO_ALREADY_EXISTS);
        }

        return mobile;
    }
    
    
    private String validatePassword(UserUpdateDto dto, Map<String, Object> updates) {

        if (!updates.containsKey("confirmPassword")) {
            log.warn("Password update failed - confirmPassword missing");
            throw new IllegalArgumentException("Confirm password required");
        }

        String password = dto.getPassword();

        if (password == null || password.isBlank() ||
            !password.matches(ValidationConstants.PASSWORD_REGEX)) {

            log.warn("Invalid password format during update");
            throw new IllegalArgumentException("Invalid password");
        }

        if (!password.equals(dto.getConfirmPassword())) {
            log.warn("Password mismatch during update");
            throw new PasswordMismatchException(AppConstants.PASSWORD_MISMATCH);
        }

        return password;
    }
	
}
