package com.springboot.ecommerce.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountStatus {
	
	ACTIVE,
	BLOCKED,
	DELETED,
	PENDING_VERIFICATION;
	
	@JsonCreator
	public static AccountStatus from(String value)
	{
		try {
			return AccountStatus.valueOf(value.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid category: " + value);
		}
	}
	

}
