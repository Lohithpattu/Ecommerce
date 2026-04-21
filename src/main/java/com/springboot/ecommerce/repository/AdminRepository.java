package com.springboot.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmail(String email);
}
