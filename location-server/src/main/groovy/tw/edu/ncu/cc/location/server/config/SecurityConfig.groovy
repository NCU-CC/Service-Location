package tw.edu.ncu.cc.location.server.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import tw.edu.ncu.cc.oauth.resource.filter.TokenAccessDecisionFilter

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    public static class OauthGuard extends WebSecurityConfigurerAdapter {

        @Autowired
        def TokenAccessDecisionFilter tokenAccessDecisionFilter

        @Override
        protected void configure( HttpSecurity http ) throws Exception {
            http.antMatcher( "/v*/**" )
                    .addFilterAfter( tokenAccessDecisionFilter, UsernamePasswordAuthenticationFilter.class )
                    .csrf().disable()
        }
    }

}
