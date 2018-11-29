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
public class RoleEndpoint {

	@Autowired
	private RoleRepository repo;
		
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Role>> getRoles(@RequestParam(required = false, value = "typ") String typ,
													@RequestParam(required = false, value = "level") Integer level){
		Iterable<Role> rolesDB = null;
		if(typ != null) {
			if(level != null) {
				rolesDB = repo.findAllByTypAndLevel(typ, level);		
			}else {
				rolesDB = repo.findAllByTyp(typ);		
			}
		}else {
			if(level != null) {
				rolesDB = repo.findAllByLevel(level);			
			}else {		
				rolesDB = repo.findAll();
			}
		}
		return new ResponseEntity<Iterable<Role>>(rolesDB, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRole(@PathVariable(required = true, name = "id") long id) {
		Optional<Role> roleDB = repo.findById(id);
		if(roleDB.isPresent()) {
			return new ResponseEntity<Role>(roleDB.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> addRole(@RequestBody(required = true) Role role){
		if(role.getId() != 0) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		repo.save(role);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updateRole(@PathVariable(required = true, name = "id") long id, @RequestBody(required = true) Role role){
		Optional<Role> roleDB = repo.findById(id);
		if(!roleDB.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		repo.save(role);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRole(@PathVariable(required = true, name = "id") long id){
		Optional<Role> roleDB = repo.findById(id);
		if(!roleDB.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		repo.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
