package hska.vslab.sec.sprindsecauthserver;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@SpringBootApplication
public class OAuth2Application {

	@Value("${security.oauth2.client.access-token-uri}")
	private String accessTokenUri;

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	@Value("${security.oauth2.client.grant-type}")
	private String grantType;

	public static void main(String[] args) {
		SpringApplication.run(OAuth2Application.class, args);
	}

	@Bean
	OAuth2RestTemplate oauth2restTemplate() {
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(accessTokenUri);
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		resourceDetails.setGrantType(grantType);
		resourceDetails.setScope(Arrays.asList("read", "write"));

		System.out.println(accessTokenUri);

		return new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
	}

}
