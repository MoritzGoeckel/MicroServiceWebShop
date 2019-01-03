package de.hska.userRoleService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndpoint {

	@Autowired
	private UserRepository repo;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getUsers(@RequestParam(required = false, value = "username") String username,
			@RequestParam(required = false, value = "firstname") String firstname,
			@RequestParam(required = false, value = "lastname") String lastname,
			@RequestParam(required = false, value = "roleId") Long roleId) {
		Iterable<User> usersDB = null;
		if (username != null) {
			if (firstname != null) {
				if (lastname != null) {
					if (roleId != null) {
						usersDB = repo.findAllByUsernameAndFirstnameAndLastnameAndRoleId(username, firstname, lastname, roleId);	
					} else {
						usersDB = repo.findAllByUsernameAndFirstnameAndLastname(username, firstname, lastname);
					}
				} else {
					if (roleId != null) {
						usersDB = repo.findAllByUsernameAndFirstnameAndRoleId(username, firstname, roleId);
					} else {
						usersDB = repo.findAllByUsernameAndFirstname(username, firstname);
					}
				}
			} else {
				if (lastname != null) {
					if (roleId != null) {
						usersDB = repo.findAllByUsernameAndLastnameAndRoleId(username, lastname, roleId);
					} else {
						usersDB = repo.findAllByUsernameAndLastname(username, lastname);
					}
				} else {
					if (roleId != null) {
						usersDB = repo.findAllByUsernameAndRoleId(username, roleId);
					} else {
						usersDB = repo.findAllByUsername(username);
					}
				}
			}
		} else {
			if (firstname != null) {
				if (lastname != null) {
					if (roleId != null) {
						usersDB = repo.findAllByFirstnameAndLastnameAndRoleId(firstname, lastname, roleId);
					} else {
						usersDB = repo.findAllByFirstnameAndLastname(firstname, lastname);
					}
				} else {
					if (roleId != null) {
						usersDB = repo.findAllByFirstnameAndRoleId(firstname, roleId);
					} else {
						usersDB = repo.findAllByFirstname(firstname);
					}
				}
			} else {
				if (lastname != null) {
					if (roleId != null) {
						usersDB = repo.findAllByLastnameAndRoleId(lastname, roleId);
					} else {
						usersDB = repo.findAllByLastname(lastname);
					}
				} else {
					if (roleId != null) {
						usersDB = repo.findAllByRoleId(roleId);
					} else {
						usersDB = repo.findAll();
					}
				}
			}
		}
		return new ResponseEntity<Iterable<User>>(usersDB, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable(required = true, name = "id") long id) {
		Optional<User> userDB = repo.findById(id);
		if (userDB.isPresent()) {
			return new ResponseEntity<User>(userDB.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> addUser(@RequestBody(required = true) User user) {
		if (user.getId() != 0) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		repo.save(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updateUser(@PathVariable(required = true, name = "id") long id, @RequestBody(required = true) User user) {
		Optional<User> userDB = repo.findById(id);
		if (!userDB.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		repo.save(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable(required = true, name = "id") long id) {
		Optional<User> userDB = repo.findById(id);
		if (!userDB.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		repo.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
