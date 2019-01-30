package hska.vslab.sec.sprindsecauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

//@RequireArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private TokenStore tokenStore;
	
	
	@Autowired
	private PasswordEncoder encoder;

	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
        	.checkTokenAccess("isAuthenticated()");
    }
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("frontendId")
		.authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
		.authorities("ROLE_USER", "ROLE_ADMIN")
		.scopes("read", "write")
		.autoApprove(true)
		.secret(encoder.encode("frontendSecret"))
		.and()
		.withClient("edgeId")
		.authorizedGrantTypes("client_credentials")
		.scopes("read", "write")
		.autoApprove(true)
		.secret(encoder.encode("edgeSecret"))
		.and()
		.withClient("sanityId")
		.authorizedGrantTypes("client_credentials")
		.scopes("read", "write")
		.autoApprove(true)
		.secret(encoder.encode("sanitySecret"))
		.and()
		.withClient("categoryId")
		.authorizedGrantTypes("client_credentials")
		.scopes("read", "write")
		.autoApprove(true)
		.secret(encoder.encode("categorySecret"))
		.and()
		.withClient("productId")
		.authorizedGrantTypes("client_credentials")
		.scopes("read", "write")
		.autoApprove(true)
		.secret(encoder.encode("productSecret"))
		.and()
		.withClient("userroleId")		
		.authorizedGrantTypes("client_credentials")
		.scopes("read", "write")
		.autoApprove(true)
		.secret(encoder.encode("userroleSecret"))
		.and()
		.withClient("oauthId")		
		.authorizedGrantTypes("client_credentials")
		.scopes("read", "write")
		.autoApprove(true)
		.secret(encoder.encode("oauthSecret"));
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore)
				.userDetailsService(userDetailsService);
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
		
	
	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new InMemoryAuthorizationCodeServices();
	}

}
