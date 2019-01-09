package de.hska.userRoleService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndpoint {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private RoleRepository repoRole;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getUsers(@RequestBody(required = false) UserQuery query) {
		Iterable<User> usersDB = null;
		if (query != null) {
			if (query.getUsername() != null) {
				if (query.getText() != null) {
					if(query.getRole() != null) {
						usersDB = repo.findAllByUsernameAndTextAndRoleID(query.getUsername(), query.getText(),
								query.getRole());			
					}else {
						usersDB = repo.findAllByUsernameAndText(query.getUsername(), query.getText());
					}
				}else {
					if(query.getRole() != null) {
						usersDB = repo.findAllByUsernameAndRoleID(query.getUsername(), query.getRole());
					}else {
						usersDB = repo.findAllByUsername(query.getUsername());
					}
				}
			}else {
				if(query.getText() != null) {
					if(query.getRole() != null) {
						usersDB = repo.findAllByTextAndRoleID(query.getText(), query.getRole());
					}else {
						usersDB = repo.findAllByText(query.getText());
					}
				}else {
					if(query.getRole() != null) {
						usersDB = repo.findAllByRoleID(query.getRole());
					}else {
						usersDB = repo.findAll();
					}
				}
			}
		} else {
			usersDB = repo.findAll();
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
	public ResponseEntity<User> addUser(@RequestBody(required = true) User user) {
		if (user.getId() != 0) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		if(user.getUsername() == null || user.getFirstname() == null || user.getLastname() == null || user.getPassword() == null || user.getRoleID() == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if(!repoRole.findById(user.getRoleID()).isPresent()) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if(repo.findAllByUsername(user.getUsername()).iterator().hasNext()) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		User userDB = repo.save(user);
		return new ResponseEntity<User>(userDB, HttpStatus.OK);
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
