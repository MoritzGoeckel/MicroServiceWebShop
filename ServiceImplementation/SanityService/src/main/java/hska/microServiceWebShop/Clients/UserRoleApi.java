package hska.microServiceWebShop.Clients;

import hska.microServiceWebShop.models.Role;
import hska.microServiceWebShop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class UserRoleApi {

	@Autowired
	private RestTemplate restTemplate;

	public List<Role> getRoles(String typ, Integer level) throws HttpClientErrorException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://userroleservice/roles");
		if(typ != null) {
			builder.queryParam("typ", typ);
		}
		if(level != null) {
			builder.queryParam("level", level.intValue());
		}
		ResponseEntity<Role[]> response = restTemplate.getForEntity(builder.toUriString(), Role[].class);
		return Arrays.asList(response.getBody());

	}
	
	public Role getRole(long id) throws HttpClientErrorException {
		ResponseEntity<Role> response = restTemplate.getForEntity("http://userroleservice/roles/" + id, Role.class);
		return response.getBody();
	}
	
	public Role createRole(Role role) throws HttpClientErrorException {
		ResponseEntity<Role> response = restTemplate.postForEntity("http://userroleservice/roles", role, Role.class);
		return response.getBody();
	}
	
	public void deleteRole(long id) throws HttpClientErrorException {
		HttpEntity<Void> request = new HttpEntity<>(null,null);
		restTemplate.exchange("http://userroleservice/roles/" + id, HttpMethod.DELETE, request, Void.class);
	}
		
	public List<User> getUsers(String username, String text, Long roleID) throws HttpClientErrorException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://userroleservice/users");
		if(username != null) {
			builder.queryParam("username", username);
		}
		if(text != null) {
			builder.queryParam("text", text);
		}
		if(roleID != null) {
			builder.queryParam("roleID", roleID);
		}
		ResponseEntity<User[]> response = restTemplate.getForEntity(builder.toUriString(), User[].class);
		return Arrays.asList(response.getBody());
	}
	
	public User getUser(long id) throws HttpClientErrorException {
		ResponseEntity<User> response = restTemplate.getForEntity("http://userroleservice/users/" + id, User.class);
		return response.getBody();
	}
	
	public User createUser(User user) throws HttpClientErrorException {
		ResponseEntity<User> response = restTemplate.postForEntity("http://userroleservice/users", user, User.class);
		return response.getBody();
	}
	
	public void deleteUser(long id) throws HttpClientErrorException {
		HttpEntity<Void> request = new HttpEntity<>(null,null);
		restTemplate.exchange("http://userroleservice/users/" + id, HttpMethod.DELETE, request, Void.class);
	}		
		
}
