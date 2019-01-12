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

import de.hska.userRoleService.exception.BadRequestException;
import de.hska.userRoleService.exception.ConflictException;
import de.hska.userRoleService.exception.NotFoundException;

@RestController
public class RoleEndpoint {

	@Autowired
	private RoleRepository repo;

	@Autowired
	private UserRepository repoUser;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Role>> getRoles(@RequestParam(name = "typ", required = false) String typ,
			@RequestParam(name = "level", required = false) Integer level) {
		Iterable<Role> rolesDB = null;
		if (typ != null) {
			if (level != null) {
				rolesDB = repo.findAllByTypAndLevel(typ, level);
			} else {
				rolesDB = repo.findAllByTyp(typ);
			}
		} else {
			if (level != null) {
				rolesDB = repo.findAllByLevel(level);
			} else {
				rolesDB = repo.findAll();
			}
		}
		return new ResponseEntity<Iterable<Role>>(rolesDB, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRole(@PathVariable(required = true, name = "id") long id) throws NotFoundException {
		Optional<Role> roleDB = repo.findById(id);
		if (!roleDB.isPresent()) {
			throw new NotFoundException("Rolle");
		}
		return new ResponseEntity<Role>(roleDB.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<Role> addRole(@RequestBody(required = true) Role role) throws ConflictException, BadRequestException {
		if (role.getLevel() == null && role.getTyp() == null) {
			throw new BadRequestException("Typ und Level fehlen");
		}else if(role.getLevel() == null) {
			throw new BadRequestException("Level fehlt");
		}else if(role.getTyp() == null) {
			throw new BadRequestException("Typ fehlt");
		}
		if (repo.findAllByTyp(role.getTyp()).iterator().hasNext()) {
			throw new ConflictException("Rolle existiert bereits");
		}
		Role roleDB = repo.save(role);
		return new ResponseEntity<Role>(roleDB, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRole(@PathVariable(required = true, name = "id") long id) throws NotFoundException, ConflictException {

		Optional<Role> roleDB = repo.findById(id);
		if (!roleDB.isPresent()) {
			throw new NotFoundException("Rolle");
		}
		Iterable<User> users = repoUser.findAllByRoleID(id);
		if (users.iterator().hasNext()) {
			throw new ConflictException("Rolle ist noch einem Benutzer zugewiesen");
		}
		repo.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
