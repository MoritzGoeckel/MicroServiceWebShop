package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import java.util.List;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.microServiceWebShop.ApiClient;
import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.api.UserRoleApi;
import hska.microServiceWebShop.models.Role;
import hska.microServiceWebShop.models.RoleQuery;
import hska.microServiceWebShop.models.User;
import hska.microServiceWebShop.models.UserBackend;
import hska.microServiceWebShop.models.UserQuery;


public class UserManagerImpl implements UserManager {
	
	UserRoleApi apiInstance;
	
	public UserManagerImpl() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://localhost:8091/api/");
		apiInstance = new UserRoleApi(apiClient);
	}

	
	public void registerUser(String username, String name, String lastname, String password, Role role) {
		UserBackend userBackend = new UserBackend();
		userBackend.setFirstName(name);
		userBackend.lastName(lastname);
		userBackend.setUsername(username);
		userBackend.setPassword(password);
		userBackend.setRoleID(role.getId());
		try {
			apiInstance.createUser(userBackend);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public User getUserByUsername(String username) {
		List<User> users = null;
		UserQuery query = new UserQuery();
		query.setUsername(username);
		try {
			users = apiInstance.getUsers(query);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users == null || users.isEmpty()) {
			return null;
		} 
		return users.get(0);
	}

	public boolean deleteUserById(int id) {
		try {
			apiInstance.deleteUser((long)id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Role getRoleByLevel(int level) {
		List<Role> roles = null;
		RoleQuery query = new RoleQuery();
		query.setLevel(level);
		try {
			roles = apiInstance.getRoles(query);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("ERROR MSG: " + e.getMessage());
			System.err.println("ERROR Res Body: " + e.getResponseBody());
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
			users = apiInstance.getUsers(query);
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
			users = apiInstance.getUsers(query);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users == null || users.isEmpty()) {
			return false;
		} 
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
