package com.springboot.ecommerce.entity;

import java.time.Instant;

import com.springboot.ecommerce.enums.AccountStatus;
import com.springboot.ecommerce.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private User user;

	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private Admin admin;

	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private Merchant merchant;
	
	@Column(nullable = false, length = 50)
    private String name;
	
	@Column(name = "mobile_no", nullable = false, unique = true, length = 15)
    private String mobileNo;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // USER, ADMIN, MERCHANT
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus; // ACTIVE, BLOCKED, DELETED, PENDING_VERIFICATION
    
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    

    @Column(nullable = false)
    private Instant updatedAt;
    
    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

}
