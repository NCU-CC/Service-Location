package tw.edu.ncu.cc.location.server.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import tw.edu.ncu.cc.oauth.resource.filter.ApiTokenDecisionFilter
import tw.edu.ncu.cc.oauth.resource.filter.TrustedApiTokenDecisionFilter

@EnableWebSecurity
public class SecurityConfig {

    @Order( 1 )
    @Configuration
    public static class OauthGuard extends WebSecurityConfigurerAdapter {

        @Autowired
        def ApiTokenDecisionFilter apiTokenDecisionFilter

        @Override
        protected void configure( HttpSecurity http ) throws Exception {
            http.requestMatchers()
                    .antMatchers( HttpMethod.GET, "/v*/**" )
                    .antMatchers( HttpMethod.POST, "/v*/**")
                    .antMatchers( HttpMethod.PUT, "/v*/**" )
                    .antMatchers( HttpMethod.DELETE, "/v*/**")
                    .and()
                    .addFilterAfter( apiTokenDecisionFilter, UsernamePasswordAuthenticationFilter.class )
                    .csrf().disable()
        }
    }

    @Order( 2 )
    @Configuration
    public static class OauthTrustedClientGuard extends WebSecurityConfigurerAdapter {

        @Autowired
        def TrustedApiTokenDecisionFilter trustedApiTokenDecisionFilter

        @Override
        protected void configure( HttpSecurity http ) throws Exception {
            http.requestMatchers()
                    .antMatchers( HttpMethod.GET, "/management/v*/faculties/**" )
                    .antMatchers( HttpMethod.POST, "/management/v*/faculties/**")
                    .antMatchers( HttpMethod.PUT, "/management/v*/faculties/**" )
                    .antMatchers( HttpMethod.DELETE, "/management/v*/faculties/**")
                    .and()
                    .addFilterAfter( trustedApiTokenDecisionFilter, UsernamePasswordAuthenticationFilter )
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
            http.requestMatchers()
                    .antMatchers( HttpMethod.GET, "/management/**" )
                    .antMatchers( HttpMethod.POST, "/management/**")
                    .antMatchers( HttpMethod.PUT, "/management/**" )
                    .antMatchers( HttpMethod.DELETE, "/management/**")
                    .and()
                    .authorizeRequests()
                        .anyRequest().access( managementAccess )
        }

    }

}
