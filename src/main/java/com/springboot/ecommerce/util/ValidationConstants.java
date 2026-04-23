package com.springboot.ecommerce.util;

public final class ValidationConstants {
	
	private ValidationConstants() {} // prevent object creation

	public static final String NAME_REQUIRED = "Name is required";
	
	public static final String BUSINESS_NAME_REQUIRED = "Business name is required";
	
	public static final String ROLE_REQUIRED = "Role is required";
	
	public static final String MOBILE_REQUIRED = "Mobile number is required";
	
	public static final String EMAIL_REQUIRED = "Email is required";
	
	public static final String CATEGORY_REQUIRED = "Category is required";
	
	public static final String ADDRESS_REQUIRED = "Address is required";
	
	public static final String GST_REQUIRED = "GST number is required";
	
	public static final String PASSWORD_REQUIRED = "Password is required";
	
	public static final String CONFIRM_PASSWORD_REQUIRED = "Confirm Password is required";
	
	public static final String NAME_SIZE = "Name should contain atleast 3 characters and maximum of 50 characters";
	
	public static final String PASSWORD_SIZE = "Password should contain minimum 8 characters and maximum of 20 characters";
	
	public static final String EMAIL_SIZE = "Email can contain maximum of 50 characters";
	
	public static final String BUSINESS_NAME_SIZE = "Business can contain maximum of 200 characters";
	
	public static final String ADDRESS_SIZE = "Address should contain minimum 3 characters and maximum of 255 characters";
	
	public static final String GST_SIZE = "GST number must contain 15 characters";
	
	public static final String DESCRIPTION_SIZE = "Description can contain maximum of 255 characters";
	
	public static final String INVALID_NAME = "Invalid Name format";
	
	public static final String INVALID_BUSINESS_NAME = "Invalid Business Name format";
	
	public static final String INVALID_MOBILE = "Invalid Mobile number";
	
	public static final String INVALID_EMAIL = "Invalid Email format";
	
	public static final String INVALID_ADDRESS = "Invalid Address format";
	
	public static final String INVALID_GST = "Invalid GST format";
	
	public static final String INVALID_DESCRIPTION = "Invalid Characters used";
	
	public static final String INVALID_PASSWORD = "Password should contain atleast 1 Uppercase character, atleast 1 Lowercase character,atleast 1 Numerical character,atleast 1 Special character";
	
	public static final String PASSWORD_MISMATCH = "Passwords do not match";
	
	public static final String PERSON_NAME_REGEX = "^[A-Za-z]+([ '-][A-Za-z]+)*$";
	
	public static final String BUSINESS_NAME_REGEX = "^[A-Za-z0-9&.,'\\- ]{2,100}$";
	
	public static final String GST_REGEX = "^[A-Z0-9]{15}$";
	
	public static final String ADDRESS_NAME_REGEX = "^[A-Za-z0-9&.,'\\- ]$";
	
	public static final String DESCRIPTION_REGEX = "^[A-Za-z0-9&.,'\\- ]$";
	
	public static final String MOBILE_REGEX = "^[6-9]\\d{9}$";
	
	public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$";
}
