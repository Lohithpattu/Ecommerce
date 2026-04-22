package com.springboot.ecommerce.exceptionhandling;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String message) {
		super(message);
		
	}	
}
