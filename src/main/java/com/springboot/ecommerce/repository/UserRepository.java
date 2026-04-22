package com.springboot.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.User;
import com.springboot.ecommerce.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByAccount_Email(String email);
	
	List<User> findByAccount_Role(Role role);

}
