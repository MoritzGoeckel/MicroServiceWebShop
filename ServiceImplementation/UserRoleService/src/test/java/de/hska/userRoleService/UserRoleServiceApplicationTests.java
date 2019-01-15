package de.hska.userRoleService;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
public class UserRoleServiceApplicationTests {

	private final static String HOST = "http://localhost:8086";
	private final static String USERRESOURCE = "/users";
	private final static String ROLERESOURCE = "/roles";

	private static final String CONFLICTCREATEROLE = "{\"message\":\"Rolle existiert bereits\"}";
	private static final String CONFLICTDELETROLE = "{\"message\":\"Rolle ist noch einem Benutzer zugewiesen\"}";
	private static final String NOTFOUNDROLE = "{\"message\":\"Rolle existiert nicht\"}";
	private static final String CONFLICTCREATEUSER = "{\"message\":\"Benutzer existiert bereits\"}";
	private static final String NOTFOUNDUSER = "{\"message\":\"Benutzer existiert nicht\"}";

	private RestTemplate rest = new RestTemplate();

	@Test
	public void createRole() {
		Role role = new Role("Admin", 1);

		ResponseEntity<Role> response = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = response.getBody();
		checkRole(role, roleRest);
		rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());

	}

	@Test
	public void createRoleTwice() {
		Role role = new Role("Admin", 1);

		Role roleRest = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class).getBody();
		try {
			rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		} catch (HttpClientErrorException e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.CONFLICT, code);
			Assert.assertEquals(CONFLICTCREATEROLE, message);
			rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());
			return;
		}
		fail();

	}

	@Test
	public void getRoles() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("Admin", 1));
		roles.add(new Role("User", 2));
		roles.add(new Role("Anonymous", 3));

		List<Role> rolesRest = new ArrayList<>();

		for (int i = 0; i < roles.size(); i++) {
			rolesRest.add(rest.postForEntity(HOST + ROLERESOURCE, roles.get(i), Role.class).getBody());
		}

		ResponseEntity<Role[]> response = rest.getForEntity(HOST + ROLERESOURCE, Role[].class);

		List<Role> rolesRestGet = Arrays.asList(response.getBody());
		checkRoles(roles, rolesRestGet);

		for (int i = 0; i < rolesRest.size(); i++) {
			rest.delete(HOST + ROLERESOURCE + "/" + rolesRest.get(i).getId());
		}

	}

	@Test
	public void getRolesByTyp() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("Admin", 1));
		roles.add(new Role("User", 2));
		roles.add(new Role("Anonymous", 3));

		List<Role> rolesRest = new ArrayList<>();

		for (int i = 0; i < roles.size(); i++) {
			rolesRest.add(rest.postForEntity(HOST + ROLERESOURCE, roles.get(i), Role.class).getBody());
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(HOST + ROLERESOURCE);
		builder.queryParam("typ", "Admin");

		ResponseEntity<Role[]> response = rest.getForEntity(builder.toUriString(), Role[].class);

		List<Role> rolesRestGet = Arrays.asList(response.getBody());
		Assert.assertEquals(1, rolesRestGet.size());
		checkRole(roles.get(0), rolesRestGet.get(0));

		for (int i = 0; i < rolesRest.size(); i++) {
			rest.delete(HOST + ROLERESOURCE + "/" + rolesRest.get(i).getId());
		}

	}

	@Test
	public void getRolesByLevel() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("Admin", 1));
		roles.add(new Role("User", 2));
		roles.add(new Role("Anonymous", 3));

		List<Role> rolesRest = new ArrayList<>();

		for (int i = 0; i < roles.size(); i++) {
			rolesRest.add(rest.postForEntity(HOST + ROLERESOURCE, roles.get(i), Role.class).getBody());
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(HOST + ROLERESOURCE);
		builder.queryParam("level", 1);

		ResponseEntity<Role[]> response = rest.getForEntity(builder.toUriString(), Role[].class);

		List<Role> rolesRestGet = Arrays.asList(response.getBody());
		Assert.assertEquals(1, rolesRestGet.size());
		checkRole(roles.get(0), rolesRestGet.get(0));

		for (int i = 0; i < rolesRest.size(); i++) {
			rest.delete(HOST + ROLERESOURCE + "/" + rolesRest.get(i).getId());
		}

	}

	@Test
	public void getRole() {
		Role role = new Role("Admin", 1);

		Role roleRest = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class).getBody();

		ResponseEntity<Role> responseGet = rest.getForEntity(HOST + ROLERESOURCE + "/" + roleRest.getId(), Role.class);
		Role roleRestGet = responseGet.getBody();
		checkRole(role, roleRestGet);

		rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());

	}

	@Test
	public void deleteRole() {
		Role role = new Role("Admin", 1);

		ResponseEntity<Role> responseAdd = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAdd.getBody();

		HttpEntity<Void> request = new HttpEntity<>(null);
		rest.exchange(HOST + ROLERESOURCE + "/" + roleRest.getId(), HttpMethod.DELETE, request, Void.class);

		try {
			rest.getForEntity(HOST + ROLERESOURCE + "/" + roleRest.getId(), Role.class);
		} catch (HttpClientErrorException e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.NOT_FOUND, code);
			Assert.assertEquals(NOTFOUNDROLE, message);
			return;
		}
		fail();

	}

	public void deleteRoleUsed() {
		Role role = new Role("Admin", 1);

		ResponseEntity<Role> responseAdd = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAdd.getBody();

		User user = new User("User", "John", "Doe", "1234", roleRest.getId());
		User userRest = rest.postForEntity(HOST + USERRESOURCE, user, User.class).getBody();

		HttpEntity<Void> request = new HttpEntity<>(null);
		try {
			rest.exchange(HOST + ROLERESOURCE + roleRest.getId(), HttpMethod.DELETE, request, Void.class);
		} catch (HttpClientErrorException e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.CONFLICT, code);
			Assert.assertEquals(CONFLICTDELETROLE, message);
			rest.delete(HOST + USERRESOURCE + "/" + userRest.getId());
			rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());
			return;
		}
		fail();

	}

	@Test
	public void createUser() {
		Role role = new Role("Admin", 1);

		ResponseEntity<Role> responseAdd = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAdd.getBody();

		User user = new User("User", "John", "Doe", "1234", roleRest.getId());
		ResponseEntity<User> response = rest.postForEntity(HOST + USERRESOURCE, user, User.class);

		User userRest = response.getBody();
		checkUser(user, userRest);

		rest.delete(HOST + USERRESOURCE + "/" + userRest.getId());
		rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());
	}

	@Test
	public void createUserTwice() {
		Role role = new Role("Admin", 1);

		ResponseEntity<Role> responseAdd = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAdd.getBody();

		User user = new User("User", "John", "Doe", "1234", roleRest.getId());
		User userRest = rest.postForEntity(HOST + USERRESOURCE, user, User.class).getBody();
		try {
			rest.postForEntity(HOST + USERRESOURCE, user, User.class);
		} catch (HttpClientErrorException e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.CONFLICT, code);
			Assert.assertEquals(CONFLICTCREATEUSER, message);
			rest.delete(HOST + USERRESOURCE + "/" + userRest.getId());
			rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());
			return;
		}
		fail();

	}

	@Test
	public void getUsers() {
		Role role1 = new Role("Admin", 1);
		Role role2 = new Role("User", 2);

		ResponseEntity<Role> responseAdd1 = rest.postForEntity(HOST + ROLERESOURCE, role1, Role.class);
		ResponseEntity<Role> responseAdd2 = rest.postForEntity(HOST + ROLERESOURCE, role2, Role.class);
		Role roleRest1 = responseAdd1.getBody();
		Role roleRest2 = responseAdd2.getBody();

		List<User> users = new ArrayList<>();
		users.add(new User("User", "John", "Doe", "1234", roleRest1.getId()));
		users.add(new User("User2", "Johnny", "Doe", "1234", roleRest1.getId()));
		users.add(new User("User3", "John", "Doey", "1234", roleRest1.getId()));
		users.add(new User("User4", "John", "Doe", "12345", roleRest1.getId()));
		users.add(new User("User5", "John", "Doe", "1234", roleRest2.getId()));

		List<User> usersRest = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			usersRest.add(rest.postForEntity(HOST + USERRESOURCE, users.get(i), User.class).getBody());
		}

		ResponseEntity<User[]> response = rest.getForEntity(HOST + USERRESOURCE, User[].class);

		List<User> usersRestGet = Arrays.asList(response.getBody());
		checkUsers(users, usersRestGet);

		for (int i = 0; i < usersRest.size(); i++) {
			rest.delete(HOST + USERRESOURCE + "/" + usersRest.get(i).getId());
		}

		rest.delete(HOST + ROLERESOURCE + "/" + roleRest1.getId());
		rest.delete(HOST + ROLERESOURCE + "/" + roleRest2.getId());

	}

	@Test
	public void getUserByUsername() {
		Role role = new Role("Admin", 1);
		ResponseEntity<Role> responseAdd = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAdd.getBody();

		List<User> users = new ArrayList<>();
		users.add(new User("User1", "John", "Doe", "1234", roleRest.getId()));
		users.add(new User("User2", "Johnny", "Doe", "1234", roleRest.getId()));
		users.add(new User("User3", "John", "Doey", "1234", roleRest.getId()));
		users.add(new User("User4", "John", "Doe", "12345", roleRest.getId()));

		List<User> usersRest = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			usersRest.add(rest.postForEntity(HOST + USERRESOURCE, users.get(i), User.class).getBody());
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(HOST + USERRESOURCE);
		builder.queryParam("username", "User1");
		ResponseEntity<User[]> response = rest.getForEntity(builder.toUriString(), User[].class);

		List<User> usersRestGet = Arrays.asList(response.getBody());
		Assert.assertEquals(1, usersRestGet.size());
		checkUser(users.get(0), usersRestGet.get(0));

		for (int i = 0; i < usersRest.size(); i++) {
			rest.delete(HOST + USERRESOURCE + "/" + usersRest.get(i).getId());
		}

		rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());

	}

	@Test
	public void getUsersByText() {
		Role role = new Role("Admin", 1);
		ResponseEntity<Role> responseAdd = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAdd.getBody();

		List<User> users = new ArrayList<>();
		users.add(new User("User1", "John", "Doe", "1234", roleRest.getId()));
		users.add(new User("User2", "Johnny", "Doe", "1234", roleRest.getId()));
		users.add(new User("User3", "Jo", "Doey", "1234", roleRest.getId()));
		users.add(new User("User4", "Johnathan", "Doe", "12345", roleRest.getId()));

		List<User> usersRest = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			usersRest.add(rest.postForEntity(HOST + USERRESOURCE, users.get(i), User.class).getBody());
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(HOST + USERRESOURCE);
		builder.queryParam("text", "John");
		ResponseEntity<User[]> response = rest.getForEntity(builder.toUriString(), User[].class);

		List<User> usersRestGet = Arrays.asList(response.getBody());
		Assert.assertEquals(1, usersRestGet.size());
		checkUser(users.get(0), usersRestGet.get(0));

		for (int i = 0; i < usersRest.size(); i++) {
			rest.delete(HOST + USERRESOURCE + "/" + usersRest.get(i).getId());
		}

		rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());

	}

	@Test
	public void getUser() {
		Role role = new Role("Admin", 1);
		ResponseEntity<Role> responseAddRole = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAddRole.getBody();

		User user = new User("User1", "John", "Doe", "1234", roleRest.getId());
		ResponseEntity<User> responseAddUser = rest.postForEntity(HOST + USERRESOURCE, user, User.class);
		User userApi = responseAddUser.getBody();

		ResponseEntity<User> response = rest.getForEntity(HOST + USERRESOURCE + "/" + userApi.getId(), User.class);

		User userRest = response.getBody();
		checkUser(user, userRest);

		rest.delete(HOST + USERRESOURCE + "/" + userRest.getId());
		rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());

	}

	@Test
	public void deleteUser() {
		Role role = new Role("Admin", 1);
		ResponseEntity<Role> responseAddRole = rest.postForEntity(HOST + ROLERESOURCE, role, Role.class);
		Role roleRest = responseAddRole.getBody();

		User user = new User("User1", "John", "Doe", "1234", roleRest.getId());
		ResponseEntity<User> responseAddUser = rest.postForEntity(HOST + USERRESOURCE, user, User.class);
		User userApi = responseAddUser.getBody();

		HttpEntity<Void> request = new HttpEntity<>(null);
		rest.exchange(HOST + USERRESOURCE + "/" + userApi.getId(), HttpMethod.DELETE, request, Void.class);

		try {
			rest.getForEntity(HOST + USERRESOURCE + "/" + userApi.getId(), User.class);
		} catch (HttpClientErrorException e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.NOT_FOUND, code);
			Assert.assertEquals(NOTFOUNDUSER, message);
			rest.delete(HOST + ROLERESOURCE + "/" + roleRest.getId());
			return;
		}
		fail();
		
	}

	private void checkRole(Role expected, Role actual) {
		if ((expected != null && actual == null) || (expected == null && actual != null)) {
			fail();
		} else if (expected != null && actual != null) {
			// Assert.assertEquals(expected.getId(), actual.getId());
			Assert.assertEquals(expected.getTyp(), actual.getTyp());
			Assert.assertEquals(expected.getLevel(), actual.getLevel());
		}
	}

	private void checkRoles(List<Role> expected, List<Role> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			checkRole(expected.get(i), actual.get(i));
		}
	}

	private void checkUser(User expected, User actual) {
		if ((expected != null && actual == null) || (expected == null && actual != null)) {
			fail();
		} else if (expected != null && actual != null) {
			// Assert.assertEquals(expected.getId(), actual.getId());
			Assert.assertEquals(expected.getUsername(), actual.getUsername());
			Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
			Assert.assertEquals(expected.getLastName(), actual.getLastName());
			Assert.assertEquals(expected.getPassword(), actual.getPassword());
			Assert.assertEquals(expected.getRoleID(), actual.getRoleID());
		}
	}

	private void checkUsers(List<User> expected, List<User> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			checkUser(expected.get(i), actual.get(i));
		}
	}

}
