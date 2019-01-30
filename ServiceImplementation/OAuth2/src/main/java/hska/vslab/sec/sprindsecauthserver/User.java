package hska.vslab.sec.sprindsecauthserver;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if(roleID == 2) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));		
		}
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
