package hska.iwi.eShopMaster.model.businessLogic.manager;

import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

public class OAuth2RestManager {
    private static OAuth2RestTemplate instance;
    static {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri("http://localhost:8092/oauth/token");
        resourceDetails.setClientId("frontendId");
        resourceDetails.setClientSecret("frontendSecret");
        resourceDetails.setGrantType("client_credentials");
        resourceDetails.setScope(Arrays.asList("read", "write"));

        instance = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
    }

    public static OAuth2RestTemplate getInstance(){
        return instance;
    }

}
