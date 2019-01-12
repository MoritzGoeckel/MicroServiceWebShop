package de.hska.userRoleService.exception;


import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		
	}
	
	public NotFoundException(String resourceName) {
		super(resourceName + " existiert nicht", HttpStatus.NOT_FOUND);
	}
	
}
