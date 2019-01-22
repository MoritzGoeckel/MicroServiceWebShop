package hska.vslab.sec.sprindsecclient;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class OauthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //  /** -> allow all my UrlS to be authorized
        http.antMatcher("/**")
                .authorizeRequests() // to be authorised
                .antMatchers("/", "/login**") // but give me some specific URL, which is having login
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
