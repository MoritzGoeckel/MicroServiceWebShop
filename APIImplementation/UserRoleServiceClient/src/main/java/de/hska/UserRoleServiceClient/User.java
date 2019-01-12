package de.hska.UserRoleServiceClient;

public class User {

	
	private long id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private Long roleID;
	
	
	public User() {
		
	}
	
	public User(String username, String firstname, String lastname, String password, Long roleID) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.roleID = roleID;
	}
	
	public long getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleID() {
		return this.roleID;
	}

	public void setRoleID(Long roleId) {
		this.roleID = roleId;
	}
	
	
}
