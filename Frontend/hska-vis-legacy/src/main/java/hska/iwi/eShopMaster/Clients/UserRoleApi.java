package hska.iwi.eShopMaster.Clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import hska.iwi.eShopMaster.models.ErrorMessage;
import hska.iwi.eShopMaster.models.Role;
import hska.iwi.eShopMaster.models.User;
import hska.iwi.eShopMaster.models.UserBackend;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

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
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "roles");
		if (typ != null) {
			builder.queryParam("typ", typ);
		}
		if (level != null) {
			builder.queryParam("level", level.intValue());
		}
		try {
			ResponseEntity<Role[]> response = restTemplate.getForEntity(builder.build().toUriString(), Role[].class);

			return Arrays.asList(response.getBody());
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public Role getRole(long id) throws ApiException {
		try {
			ResponseEntity<Role> response = restTemplate.getForEntity("http://userroleservice/roles/" + id, Role.class);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
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
		HttpEntity<Void> request = new HttpEntity<Void>(null, null);
		try {
			restTemplate.exchange("http://userroleservice/roles/" + id, HttpMethod.DELETE, request, Void.class);
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

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
			ResponseEntity<User[]> response = restTemplate.getForEntity(builder.build().toUriString(), User[].class);

			return Arrays.asList(response.getBody());
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public User getUser(long id) throws ApiException {
		try {
			ResponseEntity<User> response = restTemplate.getForEntity("http://userroleservice/users/" + id, User.class);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			handle(e);
			throw new ApiException(500, "should not happen");
		}
	}

	public User createUser(UserBackend user) throws ApiException {
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
		HttpEntity<Void> request = new HttpEntity<Void>(null, null);
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
			errorMessage = new ObjectMapper().readValue(message, ErrorMessage.class);
			throw new ApiException(e.getStatusCode().value(), errorMessage.getMessage());
		} catch (IOException IOe) {
			throw new ApiException(e.getStatusCode().value(), e.getStatusCode().name());
		}

	}

}
