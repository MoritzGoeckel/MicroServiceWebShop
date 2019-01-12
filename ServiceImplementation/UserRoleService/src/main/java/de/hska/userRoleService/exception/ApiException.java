package de.hska.userRoleService.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception{

	private static final long serialVersionUID = 1L;

	private HttpStatus code;
	
	public ApiException() {
		
	}
	
	public ApiException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}
	
	public HttpStatus getCode() {
		return this.code;
	}
}
