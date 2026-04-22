package com.springboot.ecommerce.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
	
	USER,
	ADMIN,
	MERCHANT;
	
	@JsonCreator
	public static Role from(String value) 
	{
		try {
	        return Role.valueOf(value.toUpperCase());
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Invalid role: " + value);
	    }
	}

}
