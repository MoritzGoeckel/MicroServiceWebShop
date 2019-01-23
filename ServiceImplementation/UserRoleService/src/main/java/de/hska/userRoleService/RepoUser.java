package de.hska.userRoleService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RepoUser {

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
	
	@ManyToOne
    @JoinColumn(name = "roleID")
	private Role role;
	
	
	public RepoUser() {
		
	}
	
	public RepoUser(String username, String firstname, String lastname, String password, Role role) {
		this.username = username;
		this.firstName = firstname;
		this.lastName = lastname;
		this.password = password;
		this.role = role;
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

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
