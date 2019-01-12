package de.hska.UserRoleServiceClient;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import de.hska.UserRoleServiceClient.Role;
import de.hska.UserRoleServiceClient.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleApiTests {
	
	private static final String CONFLICTCREATEROLE = "{\"message\":\"Rolle existiert bereits\"}";
	private static final String CONFLICTDELETROLE = "{\"message\":\"Rolle ist noch einem Benutzer zugewiesen\"}";
	private static final String NOTFOUNDROLE = "{\"message\":\"Rolle existiert nicht\"}";
	private static final String CONFLICTCREATEUSER = "{\"message\":\"Benutzer existiert bereits\"}";
	private static final String NOTFOUNDUSER = "{\"message\":\"Benutzer existiert nicht\"}";

	@Autowired
	private UserRoleApi api;

	@Test
	public void createRole() {
		Role role = new Role("Admin", 1);

		Role roleApi = api.createRole(role);
		checkRole(role, roleApi);
		
		api.deleteRole(roleApi.getId());

	}

	@Test
	public void createRoleTwice() {
		Role role = new Role("Admin", 1);

		Role roleApi = api.createRole(role);
		try {
			api.createRole(role);
		} catch (HttpClientErrorException  e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.CONFLICT, code);
			Assert.assertEquals(CONFLICTCREATEROLE, message);
			api.deleteRole(roleApi.getId());
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

		List<Role> rolesApi = new ArrayList<>();

		for (int i = 0; i < roles.size(); i++) {
			rolesApi.add(api.createRole(roles.get(i)));
		}

		List<Role> rolesApiGet = (List<Role>) api.getRoles(null, null);
		checkRoles(roles, rolesApiGet);

		for (int i = 0; i < rolesApi.size(); i++) {
			api.deleteRole(rolesApi.get(i).getId());
		}

	}

	@Test
	public void getRolesByTyp() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("Admin", 1));
		roles.add(new Role("User", 2));
		roles.add(new Role("Anonymous", 3));

		List<Role> rolesApi = new ArrayList<>();

		for (int i = 0; i < roles.size(); i++) {
			rolesApi.add(api.createRole(roles.get(i)));
		}

		
		List<Role> rolesApiGet = (List<Role>) api.getRoles("Admin", null);
		Assert.assertEquals(1, rolesApiGet.size());
		checkRole(roles.get(0), rolesApiGet.get(0));

		for (int i = 0; i < rolesApi.size(); i++) {
			api.deleteRole(rolesApi.get(i).getId());
		}

	}

	@Test
	public void getRolesByLevel() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("Admin", 1));
		roles.add(new Role("User", 2));
		roles.add(new Role("Anonymous", 3));

		List<Role> rolesApi = new ArrayList<>();

		for (int i = 0; i < roles.size(); i++) {
			rolesApi.add(api.createRole(roles.get(i)));
		}

		
		List<Role> rolesApiGet = (List<Role>) api.getRoles(null, 1);
		Assert.assertEquals(1, rolesApiGet.size());
		checkRole(roles.get(0), rolesApiGet.get(0));

		for (int i = 0; i < rolesApi.size(); i++) {
			api.deleteRole(rolesApi.get(i).getId());
		}

	}

	@Test
	public void getRole() {
		Role role = new Role("Admin", 1);

		Role roleApi = api.createRole(role);

		Role roleApiGet = api.getRole(roleApi.getId());
		checkRole(role, roleApiGet);

		api.deleteRole(roleApi.getId());

	}

	@Test
	public void deleteRole() {
		Role role = new Role("Admin", 1);

		Role roleApi = api.createRole(role);

		api.deleteRole(roleApi.getId());

		try {
			api.deleteRole(roleApi.getId());
		} catch (HttpClientErrorException  e) {
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

		Role roleApi = api.createRole(role);

		User user = new User("User", "John", "Doe", "1234", roleApi.getId());
		User userApi = api.createUser(user);

		try {
			api.deleteRole(roleApi.getId());
		} catch (HttpClientErrorException  e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();
			
			Assert.assertEquals(HttpStatus.CONFLICT, code);
			Assert.assertEquals(CONFLICTDELETROLE, message);
			api.deleteUser(userApi.getId());
			api.deleteRole(roleApi.getId());
			return;
		}
		fail();

	}

	@Test
	public void createUser() {
		Role role = new Role("Admin", 1);

		Role roleApi = api.createRole(role);

		User user = new User("User", "John", "Doe", "1234", roleApi.getId());
		User userApi = api.createUser(user);
		checkUser(user, userApi);

		api.deleteUser(userApi.getId());
		api.deleteRole(roleApi.getId());
	}

	@Test
	public void createUserTwice() {
		Role role = new Role("Admin", 1);
		Role roleApi = api.createRole(role);

		User user = new User("User", "John", "Doe", "1234", roleApi.getId());
		User userApi = api.createUser(user);
		try {
			api.createUser(user);
		} catch (HttpClientErrorException  e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.CONFLICT, code);
			Assert.assertEquals(CONFLICTCREATEUSER, message);
			api.deleteUser(userApi.getId());
			api.deleteRole(roleApi.getId());
			return;
		}
		fail();

	}

	@Test
	public void getUsers() {
		Role role1 = new Role("Admin", 1);
		Role role2 = new Role("User", 2);

		Role roleApi1 = api.createRole(role1);
		Role roleApi2 = api.createRole(role2);

		List<User> users = new ArrayList<>();
		users.add(new User("User", "John", "Doe", "1234", roleApi1.getId()));
		users.add(new User("User2", "Johnny", "Doe", "1234", roleApi1.getId()));
		users.add(new User("User3", "John", "Doey", "1234", roleApi1.getId()));
		users.add(new User("User4", "John", "Doe", "12345", roleApi1.getId()));
		users.add(new User("User5", "John", "Doe", "1234", roleApi2.getId()));

		List<User> usersApi = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			usersApi.add(api.createUser(users.get(i)));
		}


		List<User> usersApiGet = (List<User>) api.getUsers(null, null, null);
		checkUsers(users, usersApiGet);

		for (int i = 0; i < usersApi.size(); i++) {
			api.deleteUser(usersApi.get(i).getId());
		}

		api.deleteRole(roleApi1.getId());
		api.deleteRole(roleApi2.getId());

	}

	@Test
	public void getUserByUsername() {
		Role role = new Role("Admin", 1);
		Role roleRest = api.createRole(role);

		List<User> users = new ArrayList<>();
		users.add(new User("User1", "John", "Doe", "1234", roleRest.getId()));
		users.add(new User("User2", "Johnny", "Doe", "1234", roleRest.getId()));
		users.add(new User("User3", "John", "Doey", "1234", roleRest.getId()));
		users.add(new User("User4", "John", "Doe", "12345", roleRest.getId()));

		List<User> usersRest = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			usersRest.add(api.createUser(users.get(i)));
		}

		
		List<User> usersRestGet = (List<User>) api.getUsers("User1", null, null);
		Assert.assertEquals(1, usersRestGet.size());
		checkUser(users.get(0), usersRestGet.get(0));

		for (int i = 0; i < usersRest.size(); i++) {
			api.deleteUser(usersRest.get(i).getId());
		}

		api.deleteRole(roleRest.getId());

	}

	@Test
	public void getUsersByText() {
		Role role = new Role("Admin", 1);
		Role roleRest = api.createRole(role);

		List<User> users = new ArrayList<>();
		users.add(new User("User1", "John", "Doe", "1234", roleRest.getId()));
		users.add(new User("User2", "Johnny", "Doe", "1234", roleRest.getId()));
		users.add(new User("User3", "Jo", "Doey", "1234", roleRest.getId()));
		users.add(new User("User4", "Johnathan", "Doe", "12345", roleRest.getId()));

		List<User> usersRest = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			usersRest.add(api.createUser(users.get(i)));
		}

		List<User> usersRestGet = (List<User>) api.getUsers(null, "John", null);
		Assert.assertEquals(1, usersRestGet.size());
		checkUser(users.get(0), usersRestGet.get(0));

		for (int i = 0; i < usersRest.size(); i++) {
			api.deleteUser(usersRest.get(i).getId());
		}

		api.deleteRole(roleRest.getId());

	}

	@Test
	public void getUser() {
		Role role = new Role("Admin", 1);
		Role roleRest = api.createRole(role);

		User user = new User("User1", "John", "Doe", "1234", roleRest.getId());
		User userApi = api.createUser(user);


		User userRest = api.getUser(userApi.getId());
		checkUser(user, userRest);

		api.deleteUser(userRest.getId());
		api.deleteRole(roleRest.getId());

	}

	@Test
	public void deleteUser() {
		Role role = new Role("Admin", 1);
		Role roleRest = api.createRole(role);

		User user = new User("User1", "John", "Doe", "1234", roleRest.getId());
		User userApi = api.createUser(user);

		api.deleteUser(userApi.getId());
		
		try {
			api.deleteUser(userApi.getId());
		} catch (HttpClientErrorException  e) {
			HttpStatus code = e.getStatusCode();
			String message = e.getResponseBodyAsString();

			Assert.assertEquals(HttpStatus.NOT_FOUND, code);
			Assert.assertEquals(NOTFOUNDUSER, message);
			api.deleteRole(roleRest.getId());
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
			Assert.assertEquals(expected.getFirstname(), actual.getFirstname());
			Assert.assertEquals(expected.getLastname(), actual.getLastname());
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
