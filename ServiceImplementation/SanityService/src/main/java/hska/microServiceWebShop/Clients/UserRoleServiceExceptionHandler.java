package hska.microServiceWebShop.Clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import hska.microServiceWebShop.models.Error;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class UserRoleServiceExceptionHandler {

	@Autowired
	private ObjectMapper mapper;
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Error> handleHttpClientErrorException(HttpServletRequest request,
                                                                       HttpClientErrorException e) {
		String message = e.getResponseBodyAsString();
		ErrorMessage errorMessage;
		try {
			errorMessage = mapper.readValue(message, ErrorMessage.class);
			Error error = new Error();
			error.setDescription(errorMessage.getMessage());
			return new ResponseEntity<Error>(error, e.getStatusCode());
		} catch (IOException IOe) {
			Error error = new Error();
			error.setDescription("Unbekannte Fehlermeldung von Core Service");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
