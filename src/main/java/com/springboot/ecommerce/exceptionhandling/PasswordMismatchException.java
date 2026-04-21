package com.springboot.ecommerce.exceptionhandling;

public class PasswordMismatchException extends RuntimeException {

	public PasswordMismatchException(String message) {
		super(message);
		
	}
}
