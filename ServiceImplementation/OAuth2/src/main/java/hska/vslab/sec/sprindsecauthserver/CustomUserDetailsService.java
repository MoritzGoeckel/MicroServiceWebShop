package hska.vslab.sec.sprindsecauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = restTemplate.getForObject("http://localhost:8086/users?username=" + username, User.class);
		if(user == null) {
			throw new UsernameNotFoundException("User with username = " + username + " not found");
		}
		return user;
		
	}

}
