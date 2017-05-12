package tw.edu.ncu.cc.location.server.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//import tw.edu.ncu.cc.oauth.resource.filter.ApiTokenDecisionFilter
//import tw.edu.ncu.cc.oauth.resource.filter.TrustedApiTokenDecisionFilter
import tw.edu.ncu.cc.oauth.resource.filter.AccessTokenDecisionFilter

@EnableWebSecurity
public class SecurityConfig {

    @Order( 1 )
    @Configuration
    public static class OauthGuard extends WebSecurityConfigurerAdapter {

        @Autowired
        def AccessTokenDecisionFilter accessTokenDecisionFilter
        //def ApiTokenDecisionFilter apiTokenDecisionFilter

        @Override
        protected void configure( HttpSecurity http ) throws Exception {
            http.antMatcher( "/v*/**" )
                    .addFilterAfter( accessTokenDecisionFilter, UsernamePasswordAuthenticationFilter.class )
                    .csrf().disable()
        }
    }

    @Order( 2 )
    @Configuration
    public static class OauthTrustedClientGuard extends WebSecurityConfigurerAdapter {

        @Autowired
        def AccessTokenDecisionFilter accessTokenDecisionFilter
        //def TrustedApiTokenDecisionFilter trustedApiTokenDecisionFilter

        @Override
        protected void configure( HttpSecurity http ) throws Exception {
            http.requestMatchers()
                    .antMatchers( "/management/v*/faculties/**" )
                    .and()
                    .addFilterAfter( accessTokenDecisionFilter, UsernamePasswordAuthenticationFilter )
                    .csrf().disable()
        }

    }
    @Order( 3 )
    @Configuration
    public static class ManagementAPI extends WebSecurityConfigurerAdapter {

        @Value( '${custom.management.security.access}' )
        def String managementAccess

        @Override
        protected void configure( HttpSecurity http ) throws Exception {
            http.antMatcher( "/management/**" )
                    .authorizeRequests()
                        .anyRequest().access( managementAccess )
        }

    }

}
