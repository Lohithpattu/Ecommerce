package com.springboot.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String user_name;
    
    @Column(unique = true)
    private String mobile_no;
    
    @Column(unique = true)
    private String email;

    private String password;
}