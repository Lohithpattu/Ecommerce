package com.springboot.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.Merchant;
import com.springboot.ecommerce.enums.Role;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

	Optional<Merchant> findByAccount_Email(String email);
	
	List<Merchant> findByAccount_Role(Role role);
}
