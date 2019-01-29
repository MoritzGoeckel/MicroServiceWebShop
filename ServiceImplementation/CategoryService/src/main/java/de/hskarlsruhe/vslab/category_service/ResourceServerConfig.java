package de.hskarlsruhe.vslab.category_service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
//@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//	private static final String RESOURCE_ID = "UserRoleService";

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").authenticated()
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
//				.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
//				.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
	}

//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//		resources.tokenServices(tokenService()).resourceId(RESOURCE_ID).stateless(true);
//		
//	}
//
//	@Primary
//	@Bean
//	public RemoteTokenServices tokenService() {
//		RemoteTokenServices tokenService = new RemoteTokenServices();
//		tokenService.setCheckTokenEndpointUrl("http://localhost:8092/oauth/check_token");
//		tokenService.setClientId("userroleId");
//		tokenService.setClientSecret("userroleSecret");
//		return tokenService;
//	}

}
