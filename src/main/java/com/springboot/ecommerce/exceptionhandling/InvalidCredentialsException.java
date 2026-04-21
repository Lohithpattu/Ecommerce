package com.springboot.ecommerce.exceptionhandling;

public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException(String message) {
		super(message);
		
	}
}
