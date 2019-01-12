package de.hska.userRoleService.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException{

	private static final long serialVersionUID = 1L;

	public ConflictException() {
	}
	
	public ConflictException(String message) {
		super(message, HttpStatus.CONFLICT);
	}
	
}
