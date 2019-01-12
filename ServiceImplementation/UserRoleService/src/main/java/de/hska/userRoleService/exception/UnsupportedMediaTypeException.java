package de.hska.userRoleService.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedMediaTypeException extends ApiException {

	private static final long serialVersionUID = 1L;

	public UnsupportedMediaTypeException() {
		
	}
	
	public UnsupportedMediaTypeException(HttpStatus code) {
		super("Es wird nur Json akzeptiert", code);
	}
	
}
