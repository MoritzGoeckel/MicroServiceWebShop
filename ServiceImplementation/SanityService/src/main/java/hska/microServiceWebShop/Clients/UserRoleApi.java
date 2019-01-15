package hska.microServiceWebShop.Clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import hska.microServiceWebShop.models.Category;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.Role;
import hska.microServiceWebShop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Component
public class UserRoleApi {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	private Map<Long, Role> roleCache = new HashMap<>();
	private Map<Long, User> userCache = new HashMap<>();

	public List<Role> getRoles(String typ, Integer level) throws ApiException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://userroleservice/roles");
		if (typ != null) {
			builder.queryParam("typ", typ);
		}
		if (level != null) {
			builder.queryParam("level", level.intValue());
		}
		try {
			ResponseEntity<Role[]> response = restTemplate.getForEntity(builder.toUriString(), Role[].class);

			List<Role> roles = Arrays.asList(response.getBody());
			for (Role r : roles)
				roleCache.put(r.getId(), r);

			return roles;
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	@HystrixCommand(fallbackMethod = "getRoleCache", ignoreExceptions = ApiException.class)
	public Role getRole(long id) throws ApiException {
		try {
			ResponseEntity<Role> response = restTemplate.getForEntity("http://userroleservice/roles/" + id, Role.class);

			Role role = response.getBody();
			if (role != null)
				roleCache.put(role.getId(), role);

			return role;
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public Role getRoleCache(long id) throws ApiException {
		Role role = roleCache.getOrDefault(id, null);
		if (role == null)
			throw new ApiException(HttpStatus.NOT_FOUND.value(), "Role not found in cache");

		return role;
	}

	public Role createRole(Role role) throws ApiException {
		try {
			ResponseEntity<Role> response = restTemplate.postForEntity("http://userroleservice/roles", role,
					Role.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public void deleteRole(long id) throws ApiException {
		HttpEntity<Void> request = new HttpEntity<>(null, null);
		try {
			restTemplate.exchange("http://userroleservice/roles/" + id, HttpMethod.DELETE, request, Void.class);
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	@HystrixCommand(fallbackMethod = "getUsersCache", ignoreExceptions = ApiException.class)
	public List<User> getUsers(String username, String text, Long roleID) throws ApiException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://userroleservice/users");
		if (username != null && !username.isEmpty()) {
			builder.queryParam("username", username);
		}
		if (text != null && !text.isEmpty()) {
			builder.queryParam("text", text);
		}
		if (roleID != null) {
			builder.queryParam("roleID", roleID);
		}
		try {
			ResponseEntity<User[]> response = restTemplate.getForEntity(builder.toUriString(), User[].class);

			List<User> users = Arrays.asList(response.getBody());
			for (User u : users)
				userCache.put(u.getId(), u);

			return users;
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	// public List<User> getUsersCache(String username, String text, Long roleID){

	// }

	@HystrixCommand(fallbackMethod = "getUserCache", ignoreExceptions = ApiException.class)
	public User getUser(long id) throws ApiException {
		try {
			ResponseEntity<User> response = restTemplate.getForEntity("http://userroleservice/users/" + id, User.class);

			User user = response.getBody();
			if (user != null)
				userCache.put(user.getId(), user);
			return user;
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public User getUserCache(long id) throws ApiException {
		User user = userCache.getOrDefault(id, null);
		if (user == null)
			throw new ApiException(HttpStatus.NOT_FOUND.value(), "User not found in cache");
		return user;
	}

	public User createUser(User user) throws ApiException {
		try {
			ResponseEntity<User> response = restTemplate.postForEntity("http://userroleservice/users", user,
					User.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public void deleteUser(long id) throws ApiException {
		HttpEntity<Void> request = new HttpEntity<>(null, null);
		try {
			restTemplate.exchange("http://userroleservice/users/" + id, HttpMethod.DELETE, request, Void.class);
		} catch (HttpClientErrorException e) {
			handle(e);
		}
	}

	private void handle(HttpClientErrorException e) throws ApiException {
		String message = e.getResponseBodyAsString();
		ErrorMessage errorMessage;
		try {
			errorMessage = mapper.readValue(message, ErrorMessage.class);
			Error error = new Error();
			error.setDescription(errorMessage.getMessage());
			throw new ApiException(e.getStatusCode().value(), errorMessage.getMessage());
		} catch (IOException IOe) {
			throw new ApiException(e.getStatusCode().value(), e.getStatusCode().name());
		}

	}

}
