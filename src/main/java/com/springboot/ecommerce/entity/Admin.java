package com.springboot.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    private String admin_name;
    
    @Column(unique = true)
    private String mobile_no;
    
    @Column(unique = true)
    private String email;

    private String password;
}