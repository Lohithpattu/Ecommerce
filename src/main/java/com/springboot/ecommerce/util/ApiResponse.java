package com.springboot.ecommerce.util;

public class ApiResponse {

	private String message;
	private int status;
	private Object data;
	
	public ApiResponse(String message, int status, Object data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}
}
