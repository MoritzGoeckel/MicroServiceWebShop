package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.UserManagerImpl;
import hska.iwi.eShopMaster.models.User;

import java.util.Arrays;
import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -983183915002226000L;
	private String username = null;
	private String password = null;
	private String firstname;
	private String lastname;
	private String role;

	@Override
	public String execute() throws Exception {

		// Return string:
		String result = "input";

		Map<String, Object> session = ActionContext.getContext().getSession();

		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
		resource.setPassword(getPassword());
		resource.setUsername(getUsername());
		resource.setAccessTokenUri("http://localhost:8092/oauth/token");
		resource.setClientId("frontendId");
		resource.setClientSecret("frontendSecret");
		resource.setGrantType("password");
		resource.setScope(Arrays.asList("read", "write"));

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource);

		UserManager myCManager = new UserManagerImpl(restTemplate);

		// Get user from DB:
		try {
			User user = myCManager.getUserByUsername(getUsername());
			session.put("restTemplate", restTemplate);
			session.put("webshop_user", user);
			session.put("message", "");

			firstname = user.getFirstName();
			lastname = user.getLastName();
			role = user.getRole().getTyp();
			result = "success";
		} catch (Exception e) {
			addActionError(getText("error.password.wrong"));
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void validate() {
		if (getUsername().length() == 0) {
			addActionError(getText("error.username.required"));
		}
		if (getPassword().length() == 0) {
			addActionError(getText("error.password.required"));
		}
	}

	public String getUsername() {
		return (this.username);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return (this.password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
