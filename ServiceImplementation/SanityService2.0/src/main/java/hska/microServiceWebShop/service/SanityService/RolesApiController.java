package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.ApiException;
import hska.microServiceWebShop.Clients.UserRoleApi;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.Role;
import hska.microServiceWebShop.models.RoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
public class RolesApiController{

	@Autowired
	private UserRoleApi userRoleAPIClient;

	@RequestMapping(value = "/roles", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<?> createRole(@RequestBody Role role) {
		try {
			Role r = userRoleAPIClient.createRole(role);
			return new ResponseEntity<Role>(r, HttpStatus.OK);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
		try {
			Role r = userRoleAPIClient.getRole(id);
			userRoleAPIClient.deleteRole(id);
			return ResponseEntity.ok().body(r);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/roles/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getRoleById(@PathVariable("id") Long id) {
		try {
			Role r = userRoleAPIClient.getRole(id);
			return ResponseEntity.ok().body(r);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/roles", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getRoles(@RequestHeader(value = "Text", defaultValue = "") String text,
			@RequestHeader(value = "Level", defaultValue = "") Integer level) {
		RoleQuery query = new RoleQuery();
		query.setText(text);
		query.setLevel(level);
		try {
			List<Role> rs = userRoleAPIClient.getRoles(query.getText(), query.getLevel());
			return new ResponseEntity<List<Role>>(rs, HttpStatus.OK);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

}
