package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import hska.iwi.eShopMaster.Clients.ApiException;
import hska.iwi.eShopMaster.Clients.UserRoleApi;
import hska.iwi.eShopMaster.model.businessLogic.manager.OAuth2RestManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.models.*;

public class UserManagerImpl implements UserManager {
	
	private UserRoleApi apiInstance;
	
	public UserManagerImpl(OAuth2RestTemplate restTemplate) {
		apiInstance = new UserRoleApi(restTemplate);
	}

	
	public void registerUser(String username, String name, String lastname, String password, Long role) {
		UserBackend userBackend = new UserBackend();
		userBackend.setFirstName(name);
		userBackend.lastName(lastname);
		userBackend.setUsername(username);
		userBackend.setPassword(password);
		userBackend.setRoleID(role);
		try {
			apiInstance.createUser(userBackend);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public User getUserByUsername(String username) {
		List<User> users = null;
		try {
			users = apiInstance.getUsers(username, null, null);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users == null || users.isEmpty()) {
			return null;
		} 
		return users.get(0);
	}

	public boolean deleteUserById(long id) {
		try {
			apiInstance.deleteUser(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Role getRoleByLevel(int level) {
		List<Role> roles = null;
		try {
			roles = apiInstance.getRoles(null, level);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR MSG: " + e.getMessage());
			System.err.println("ERROR Code: " + e.getCode());
		}
		if(roles == null || roles.isEmpty()) {
			System.err.println("Aufruf gleich NULL!!!!!!!!!!!!!");
			return null;
		}
		return roles.get(0);
	}

	public boolean doesUserAlreadyExist(String username) {
		List<User> users = null;
		UserQuery query = new UserQuery();
		query.setUsername(username);
		try {
			users = apiInstance.getUsers(username, null, null);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users == null || users.isEmpty()) {
			return false;
		} 
		return true;
	}
	

	public boolean validate(User user) {
		List<User> users = null;
		UserQuery query = new UserQuery();
		query.setUsername(user.getUsername());
		try {
			users = apiInstance.getUsers(user.getUsername(), null, null);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users == null || users.isEmpty()) {
			return false;
		}
		for (User user1 : users) {
			if (user1.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
