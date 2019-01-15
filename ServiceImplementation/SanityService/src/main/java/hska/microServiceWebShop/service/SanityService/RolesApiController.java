package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.ApiException;
import hska.microServiceWebShop.Clients.UserRoleApi;
import hska.microServiceWebShop.models.Category;
import hska.microServiceWebShop.models.CategoryQuery;
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

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

@Controller
public class RolesApiController implements RolesApi {

	@Autowired
	private UserRoleApi userRoleAPIClient;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public RolesApiController(HttpServletRequest request) {
		this.request = request;
	}

	public ResponseEntity createRole(@RequestBody Role role) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			Role r = userRoleAPIClient.createRole(role);
			return new ResponseEntity<Role>(r, HttpStatus.OK);
		}

		Error error = new Error();
		error.description("wrong acept datatye");
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity deleteRole(@PathVariable("id") Long id) {
		String accept = request.getHeader("Accept");

		Role r = userRoleAPIClient.getRole(id);
		userRoleAPIClient.deleteRole(id);
		return ResponseEntity.ok().body(r);
	}

	public ResponseEntity getRoleById(@PathVariable("id") Long id) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			Role r = userRoleAPIClient.getRole(id);
			return ResponseEntity.ok().body(r);
		}

		Error error = new Error();
		error.description("wrong acept datatye");
		return ResponseEntity.badRequest().body(error);
	}

	public ResponseEntity getRoles(@RequestHeader(value = "Text", defaultValue = "") String text,
			@RequestHeader(value = "Level", defaultValue = "") Integer level) {
		RoleQuery query = new RoleQuery();
		query.setText(text);
		query.setLevel(level);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            	List<Role> rs = userRoleAPIClient.getRoles(query.getText(), query.getLevel());
                return new ResponseEntity<List<Role>>(rs,HttpStatus.OK);
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
	}

}
