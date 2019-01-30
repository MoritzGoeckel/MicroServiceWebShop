package hska.iwi.eShopMaster.Clients;

import com.fasterxml.jackson.databind.ObjectMapper;

import hska.iwi.eShopMaster.models.ErrorMessage;
import hska.iwi.eShopMaster.models.Role;
import hska.iwi.eShopMaster.models.User;
import hska.iwi.eShopMaster.models.UserBackend;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserRoleApi {

	private final String baseUrl;
	private OAuth2RestTemplate restTemplate;

	public UserRoleApi(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.baseUrl = "http://" + "localhost:8091" + "/api/";
	}

    public List<Role> getRoles(String typ, Integer level) throws ApiException {
    	HttpHeaders headers = new HttpHeaders();
		if (typ != null) {
			headers.add("typ", typ);
		}
		if (level != null) {
			headers.add("level", level.toString());
		}
		try {
			ResponseEntity<Role[]> response = restTemplate.exchange(baseUrl + "role", HttpMethod.GET,
					new HttpEntity<String>(headers), Role[].class);

			return Arrays.asList(response.getBody());
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public Role getRole(long id) throws ApiException {
		try {
			ResponseEntity<Role> response = restTemplate.getForEntity(baseUrl + "roles/" + id, Role.class);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public Role createRole(Role role) throws ApiException {
		try {
			ResponseEntity<Role> response = restTemplate.postForEntity(baseUrl + "roles", role,
					Role.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public void deleteRole(long id) throws ApiException {
		HttpEntity<Void> request = new HttpEntity<Void>(null, null);
		try {
			restTemplate.exchange(baseUrl + "roles/" + id, HttpMethod.DELETE, request, Void.class);
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public List<User> getUsers(String username, String text, Long roleID) throws ApiException {
    	HttpHeaders headers = new HttpHeaders();
		if (username != null && !username.isEmpty()) {
			headers.add("username", username);
		}
		if (text != null && !text.isEmpty()) {
			headers.add("text", text);
		}
		if (roleID != null) {
			headers.add("roleID", roleID.toString());
		}
		try {
			ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "role", HttpMethod.GET,
					new HttpEntity<String>(headers), User[].class);
			return Arrays.asList(response.getBody());
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public User getUser(long id) throws ApiException {
		try {
			ResponseEntity<User> response = restTemplate.getForEntity(baseUrl + "users/" + id, User.class);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public User createUser(UserBackend user) throws ApiException {
		try {
			ResponseEntity<User> response = restTemplate.postForEntity(baseUrl + "users", user,
					User.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public void deleteUser(long id) throws ApiException {
		HttpEntity<Void> request = new HttpEntity<Void>(null, null);
		try {
			restTemplate.exchange(baseUrl + "users/" + id, HttpMethod.DELETE, request, Void.class);
		} catch (HttpClientErrorException e) {
			handle(e);
		}
	}

	private void handle(HttpClientErrorException e) throws ApiException {
		String message = e.getResponseBodyAsString();
		ErrorMessage errorMessage;
		try {
			errorMessage = new ObjectMapper().readValue(message, ErrorMessage.class);
			throw new ApiException(e.getStatusCode().value(), errorMessage.getMessage());
		} catch (IOException IOe) {
			throw new ApiException(e.getStatusCode().value(), e.getStatusCode().name());
		}

	}

}
