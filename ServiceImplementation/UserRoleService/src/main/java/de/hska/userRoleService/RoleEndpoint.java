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
public class RoleEndpoint {

	@Autowired
	private RoleRepository repo;
	
	@Autowired
	private UserRepository repoUser;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Role>> getRoles(@RequestBody(required = false) RoleQuery query) {
		Iterable<Role> rolesDB = null;
		if (query != null) {
			if (query.getTyp() != null) {
				if (query.getLevel() != null) {
					rolesDB = repo.findAllByTypAndLevel(query.getTyp(), query.getLevel());
				} else {
					rolesDB = repo.findAllByTyp(query.getTyp());
				}
			} else {
				if (query.getLevel() != null) {
					rolesDB = repo.findAllByLevel(query.getLevel());
				} else {
					rolesDB = repo.findAll();
				}
			}
		}else {
			rolesDB = repo.findAll();
		}
		return new ResponseEntity<Iterable<Role>>(rolesDB, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRole(@PathVariable(required = true, name = "id") long id) {		
		Optional<Role> roleDB = repo.findById(id);
		if (roleDB.isPresent()) {
			return new ResponseEntity<Role>(roleDB.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<Role> addRole(@RequestBody(required = true) Role role) {
		if (role.getId() != 0) {
			return new ResponseEntity<Role>(HttpStatus.CONFLICT);
		}
		if(role.getLevel() == null || role.getTyp() == null) {
			return new ResponseEntity<Role>(HttpStatus.BAD_REQUEST);
		}
		if(repo.findAllByTyp(role.getTyp()).iterator().hasNext()) {
			return new ResponseEntity<Role>(HttpStatus.CONFLICT);
		}
		Role roleDB = repo.save(role);
		return new ResponseEntity<Role>(roleDB, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRole(@PathVariable(required = true, name = "id") long id) {
		
		Optional<Role> roleDB = repo.findById(id);
		if (!roleDB.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Iterable<User> users = repoUser.findAllByRoleID(id);
		if(users.iterator().hasNext()) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		repo.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
