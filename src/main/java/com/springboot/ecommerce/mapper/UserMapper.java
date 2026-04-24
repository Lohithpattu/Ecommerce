package com.springboot.ecommerce.mapper;

import org.springframework.stereotype.Component;

import com.springboot.ecommerce.dto.UserRegisterDto;
import com.springboot.ecommerce.dto.UserResponseDto;
import com.springboot.ecommerce.entity.Account;
import com.springboot.ecommerce.entity.User;
import com.springboot.ecommerce.enums.AccountStatus;
import com.springboot.ecommerce.enums.Role;
import com.springboot.ecommerce.util.Normalizer;

@Component
public class UserMapper {

    public Account toAccount(UserRegisterDto dto) {
        String email = Normalizer.normalizeEmail(dto.getEmail());
        String mobile = Normalizer.normalizeMobile(dto.getMobileNo());

        Account account = new Account();
        account.setRole(Role.USER);
        account.setName(dto.getName());
        account.setEmail(email);
        account.setMobileNo(mobile);
        account.setPassword(dto.getPassword());
        account.setAccountStatus(AccountStatus.PENDING_VERIFICATION);

        return account;
    }

    public User toUser(Account account) {
        User user = new User();
        user.setAccount(account);
        account.setUser(user);
        return user;
    }
    
    public UserResponseDto toResponseDto(User user) {

        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setName(user.getAccount().getName());
        dto.setEmail(user.getAccount().getEmail());
        dto.setMobileNo(user.getAccount().getMobileNo());
        dto.setAccountStatus(user.getAccount().getAccountStatus());

        return dto;
    }
}
