package de.hska.userRoleService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
    @GeneratedValue
    @Column(name="id")
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="roleID")
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

	public void setRoleID(Long roleId) {
		this.roleID = roleId;
	}
	
	
}
