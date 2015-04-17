package tw.edu.ncu.cc.location.server.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    public static class OauthGuard extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure( HttpSecurity http ) throws Exception {
//            http.antMatcher( "/management/**" )
//                .authorizeRequests()
//                    .antMatchers( "/management/v1/**" ).access( "hasIpAddress('127.0.0.1') or hasIpAddress('0:0:0:0:0:0:0:1')" )
//                    .and()
//                .csrf().disable()
        }
    }

}
