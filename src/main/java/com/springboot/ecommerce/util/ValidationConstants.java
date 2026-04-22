package com.springboot.ecommerce.util;

public final class ValidationConstants {
	
	private ValidationConstants() {} // prevent object creation

	public static final String USER_NAME_REQUIRED = "User name is required";
	
	public static final String ADMIN_NAME_REQUIRED = "Admin name is required";
	
	public static final String MERCHANT_NAME_REQUIRED = "Merchant name is required";
	
	public static final String BUSINESS_NAME_REQUIRED = "Business name is required";
	
	public static final String ROLE_REQUIRED = "Role is required";
	
	public static final String MOBILE_REQUIRED = "Mobile number is required";
	
	public static final String EMAIL_REQUIRED = "Email is required";
	
	public static final String PASSWORD_REQUIRED = "Password is required";
	
	public static final String CONFIRM_PASSWORD_REQUIRED = "Confirm Password is required";
	
	public static final String NAME_SIZE = "Name should contain atleast 3 characters and maximum of 20 characters";
	
	public static final String PASSWORD_SIZE = "Password should contain minimum 8 characters and maximum of 20 characters";
	
	public static final String EMAIL_SIZE = "Email can contain maximum of 50 characters";
	
	public static final String INVALID_NAME = "Invalid Name format";
	
	public static final String INVALID_MOBILE = "Invalid Mobile number";
	
	public static final String INVALID_EMAIL = "Invalid Email format";
	
	public static final String INVALID_PASSWORD = "Password should contain atleast 1 Uppercase character, atleast 1 Lowercase character,atleast 1 Numerical character,atleast 1 Special character";
	
	public static final String PASSWORD_MISMATCH = "Passwords do not match";
	
	public static final String PERSON_NAME_REGEX = "^[A-Za-z]+([ '-][A-Za-z]+)*$";
	
	public static final String MOBILE_REGEX = "^[6-9]\\d{9}$";
	
	public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$";
}
