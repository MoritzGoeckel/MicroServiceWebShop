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
public class UserEndpoint {

	@Autowired
	private UserRepository repo;

	@Autowired
	private RoleRepository repoRole;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getUsers(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "text", required = false) String text,
			@RequestParam(name = "roleID", required = false) Long roleID) {
		Iterable<User> usersDB = null;
		if (username != null && !username.isEmpty()) {
			if (text != null && !text.isEmpty()) {
				if (roleID != null) {
					usersDB = repo.findAllByUsernameAndTextAndRoleID(username, text, roleID);
				} else {
					usersDB = repo.findAllByUsernameAndText(username, text);
				}
			} else {
				if (roleID != null) {
					usersDB = repo.findAllByUsernameAndRoleID(username, roleID);
				} else {
					usersDB = repo.findAllByUsername(username);
				}
			}
		} else {
			if (text != null && !text.isEmpty()) {
				if (roleID != null) {
					usersDB = repo.findAllByTextAndRoleID(text, roleID);
				} else {
					usersDB = repo.findAllByText(text);
				}
			} else {
				if (roleID != null) {
					usersDB = repo.findAllByRoleID(roleID);
				} else {
					usersDB = repo.findAll();
				}
			}
		}
		return new ResponseEntity<Iterable<User>>(usersDB, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable(required = true, name = "id") long id) throws NotFoundException {
		Optional<User> userDB = repo.findById(id);
		if (!userDB.isPresent()) {
			throw new NotFoundException("Benutzer");
		}
		return new ResponseEntity<User>(userDB.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<User> addUser(@RequestBody(required = true) User user) throws BadRequestException, ConflictException {
		checkPostBody(user);
		if (repo.findAllByUsername(user.getUsername()).iterator().hasNext()) {
			throw new ConflictException("Benutzer existiert bereits");
		}
		User userDB = repo.save(user);
		return new ResponseEntity<User>(userDB, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable(required = true, name = "id") long id) throws NotFoundException {
		Optional<User> userDB = repo.findById(id);
		if (!userDB.isPresent()) {
			throw new NotFoundException("Benutzer");
		}
		repo.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private void checkPostBody(User user) throws BadRequestException {
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			if (user.getFirstname() == null || user.getFirstname().isEmpty()) {
				if (user.getLastname() == null || user.getLastname().isEmpty()) {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException(
									"Benutzername, Vorname, Nachname, Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername, Vorname, Nachname und Passwort fehlen");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Benutzername, Vorname, Nachname und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername, Vorname und Nachname fehlen");
						}
					}
				} else {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Benutzername, Vorname, Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername, Vorname und Passwort fehlen");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Benutzername, Vorname und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername und Vorname fehlen");
						}
					}
				}
			} else {
				if (user.getLastname() == null || user.getLastname().isEmpty()) {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Benutzername, Nachname, Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername, Nachname und Passwort fehlen");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Benutzername, Nachname und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername und Nachname fehlen");
						}
					}
				} else {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Benutzername, Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername und Passwort fehlen");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Benutzername und RolleID fehlen");
						} else {
							throw new BadRequestException("Benutzername fehlt");
						}
					}
				}
			}
		} else {
			if (user.getFirstname() == null || user.getFirstname().isEmpty()) {
				if (user.getLastname() == null || user.getLastname().isEmpty()) {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Vorname, Nachname, Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Vorname, Nachname und Passwort fehlen");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Vorname, Nachname und RolleID fehlen");
						} else {
							throw new BadRequestException("Vorname und Nachname fehlen");
						}
					}
				} else {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Vorname, Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Vorname und Passwort fehlen");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Vorname und RolleID fehlen");
						} else {
							throw new BadRequestException("Vorname fehlt");
						}
					}
				}
			} else {
				if (user.getLastname() == null || user.getLastname().isEmpty()) {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Nachname, Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Nachname und Passwort fehlen");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Nachname und RolleID fehlen");
						} else {
							throw new BadRequestException("Nachname fehlt");
						}
					}
				} else {
					if (user.getPassword() == null || user.getPassword().isEmpty()) {
						if (user.getRoleID() == null) {
							throw new BadRequestException("Passwort und RolleID fehlen");
						} else {
							throw new BadRequestException("Passwort fehlt");
						}
					} else {
						if (user.getRoleID() == null) {
							throw new BadRequestException("RolleID fehlt");
						} else {
							// Requestbody complete
						}
					}
				}
			}
		}

		if (!repoRole.findById(user.getRoleID()).isPresent()) {
			throw new BadRequestException("Rolle existiert nicht");
		}
	}

}
