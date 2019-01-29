package de.hska.userRoleService.model;


public class User {

	private long id;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private Long roleID;
	
	
	public User() {
		
	}
	
	public User(String username, String firstname, String lastname, String password, Long roleID) {
		this.username = username;
		this.firstName = firstname;
		this.lastName = lastname;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
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

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}
	
	
}
