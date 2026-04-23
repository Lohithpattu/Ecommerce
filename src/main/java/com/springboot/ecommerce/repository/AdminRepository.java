package com.springboot.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.Admin;
import com.springboot.ecommerce.enums.Role;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByAccount_Email(String email);
	
	List<Admin> findByAccount_Role(Role role);
}
