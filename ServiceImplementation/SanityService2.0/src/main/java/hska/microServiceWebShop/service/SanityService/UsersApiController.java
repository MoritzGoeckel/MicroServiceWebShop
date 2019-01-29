package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.ApiException;
import hska.microServiceWebShop.Clients.UserRoleApi;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.User;
import hska.microServiceWebShop.models.UserBackend;
import hska.microServiceWebShop.models.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersApiController {

	@Autowired
	private UserRoleApi userRoleAPIClient;

	@RequestMapping(value = "/users", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserBackend user) {
		try {
			User u = userRoleAPIClient.createUser(user);
			return ResponseEntity.ok().body(u);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		try {
			User u = userRoleAPIClient.getUser(id);
			userRoleAPIClient.deleteUser(id);
			return ResponseEntity.ok().body(u);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/users/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
		try {
			User u = userRoleAPIClient.getUser(id);
			return ResponseEntity.ok().body(u);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/users", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getUsers(@RequestHeader(value = "Text", defaultValue = "") String text,
			@RequestHeader(value = "Role", defaultValue = "") Long roleId,
			@RequestHeader(value = "Username", defaultValue = "") String name) {
		UserQuery query = new UserQuery();
		query.setText(text);
		query.setUsername(name);
		query.setRole(roleId);
		try {
			List<User> us = userRoleAPIClient.getUsers(query.getUsername(), query.getText(), query.getRole());
			return ResponseEntity.ok().body(us);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

}
