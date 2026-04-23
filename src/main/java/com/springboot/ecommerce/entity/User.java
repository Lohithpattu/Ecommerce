package com.springboot.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id",nullable = false, unique = true)
    private Account account;
    
}