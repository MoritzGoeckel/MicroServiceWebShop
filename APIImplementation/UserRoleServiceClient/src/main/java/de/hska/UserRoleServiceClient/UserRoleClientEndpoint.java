package de.hska.UserRoleServiceClient;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class UserRoleClientEndpoint {


	@Autowired
	private UserRoleApi api;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Role>> getRoles(@RequestParam(name = "typ", required = false) String typ,
			@RequestParam(name = "level", required = false) Integer level) throws HttpClientErrorException {
		Collection<Role> roles = api.getRoles(typ, level);
		return new ResponseEntity<Iterable<Role>>(roles, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRole(@PathVariable(required = true, name = "id") long id)
			throws HttpClientErrorException {
		Role role = api.getRole(id);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<Role> addRole(@RequestBody(required = true) Role role) throws HttpClientErrorException {
		Role roleApi = api.createRole(role);
		return new ResponseEntity<Role>(roleApi, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRole(@PathVariable(required = true, name = "id") long id)
			throws HttpClientErrorException {
		api.deleteRole(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getUsers(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "text", required = false) String text,
			@RequestParam(name = "roleID", required = false) Long roleID) throws HttpClientErrorException {
		Collection<User> users = api.getUsers(username, text, roleID);
		return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable(required = true, name = "id") long id)
			throws HttpClientErrorException {
		User user = api.getUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<User> addUser(@RequestBody(required = true) User user) throws HttpClientErrorException {
		User userApi = api.createUser(user);
		return new ResponseEntity<User>(userApi, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable(required = true, name = "id") long id)
			throws HttpClientErrorException {
		api.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
