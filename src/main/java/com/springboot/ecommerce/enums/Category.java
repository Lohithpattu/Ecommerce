package com.springboot.ecommerce.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
	
	FOOD,
	GROCERY,
	ELECTRONICS,
	CLOTHING;
	
	@JsonCreator
	public static Category from(String value)
	{
		try {
			
			return Category.valueOf(value.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid category: " + value);
		}
	
	}
}

