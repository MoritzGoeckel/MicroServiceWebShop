package de.hska.userRoleService;

public class UserQuery {

	private String username;
	private String text;
	private Long role;
	
	public UserQuery() {
		
	}
	
	public UserQuery(String username, String text, Long role) {
		this.username = username;
		this.text = text;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}
	
	
}
