package hska.vslab.sec.sprindsecauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User[] users = restTemplate.getForObject("http://localhost:8086/users?username=" + username, User[].class);
		if(users == null || users.length == 0) {
			throw new UsernameNotFoundException("User with username = " + username + " not found");
		}
		
		User user = users[0];
		user.setPassword(encoder.encode(user.getPassword()));
		
		return user;
		
	}

}
