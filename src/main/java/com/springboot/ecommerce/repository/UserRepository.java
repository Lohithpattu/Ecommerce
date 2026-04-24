package com.springboot.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.User;
import com.springboot.ecommerce.enums.AccountStatus;
import com.springboot.ecommerce.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByAccount_EmailOrAccount_MobileNo(String email, String mobileNo);
	
	boolean existsByAccount_MobileNo(String mobileNo);
	
	Optional<User> findByAccount_Email(String email);
	
	Optional<User> findByAccount_MobileNo(String mobileNo);
	
	List<User> findByAccount_Role(Role role);
	
	List<User> findByAccount_RoleAndAccount_AccountStatus(Role role, AccountStatus accountStatus);

}
