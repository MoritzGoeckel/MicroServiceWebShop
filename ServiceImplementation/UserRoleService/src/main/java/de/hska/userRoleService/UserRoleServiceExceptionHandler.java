package de.hska.userRoleService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.hska.userRoleService.exception.BadRequestException;
import de.hska.userRoleService.exception.ConflictException;
import de.hska.userRoleService.exception.NotFoundException;
import de.hska.userRoleService.exception.UnsupportedMediaTypeException;
import de.hska.userRoleService.model.ErrorMessage;

@ControllerAdvice
public class UserRoleServiceExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorMessage> handleBadRequestException(HttpServletRequest request,
			BadRequestException e) {
		String message = null;
		if (e.getCause() != null) {
			message = e.getCause().getMessage();
		} else {
			message = e.getMessage();
		}
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(message);
		
		return new ResponseEntity<>(errorMessage, e.getCode());
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessage> handleNotFoundException(HttpServletRequest request,
			NotFoundException e) {
		String message = null;
		if (e.getCause() != null) {
			message = e.getCause().getMessage();
		} else {
			message = e.getMessage();
		}
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(message);
		
		return new ResponseEntity<>(errorMessage, e.getCode());
	}
	
	@ExceptionHandler(UnsupportedMediaTypeException.class)
	public ResponseEntity<ErrorMessage> handleUnsupportedMediaTypeException(HttpServletRequest request,
			UnsupportedMediaTypeException e) {
		String message = null;
		if (e.getCause() != null) {
			message = e.getCause().getMessage();
		} else {
			message = e.getMessage();
		}
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(message);
		
		return new ResponseEntity<>(errorMessage, e.getCode());
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorMessage> handleConflictException(HttpServletRequest request,
			ConflictException e) {
		String message = null;
		if (e.getCause() != null) {
			message = e.getCause().getMessage();
		} else {
			message = e.getMessage();
		}
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(message);
		
		return new ResponseEntity<>(errorMessage, e.getCode());
	}

}
