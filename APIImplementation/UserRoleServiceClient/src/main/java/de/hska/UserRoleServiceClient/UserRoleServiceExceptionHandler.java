package de.hska.UserRoleServiceClient;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class UserRoleServiceExceptionHandler {

	@Autowired
	private ObjectMapper mapper;
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ErrorMessage> handleHttpClientErrorException(HttpServletRequest request,
			HttpClientErrorException e) {
		String message = e.getResponseBodyAsString();
		ErrorMessage errorMessage;
		try {
			errorMessage = mapper.readValue(message, ErrorMessage.class);
			return new ResponseEntity<>(errorMessage, e.getStatusCode());
		} catch (IOException IOe) {
			return new ResponseEntity<>(new ErrorMessage("Unbekannte Fehlermeldung von Core Service"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
