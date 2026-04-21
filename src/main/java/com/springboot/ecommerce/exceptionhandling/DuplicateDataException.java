package com.springboot.ecommerce.exceptionhandling;

public class DuplicateDataException extends RuntimeException {

	public DuplicateDataException(String message) {
		super(message);
		
	}
}
