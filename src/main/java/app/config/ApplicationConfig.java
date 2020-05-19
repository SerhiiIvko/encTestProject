package app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@ComponentScan(value = {"app"})
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String appMainUrlPattern = "/**";
        final String authUrlPattern = "/";
        http
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .antMatchers(HttpMethod.POST, appMainUrlPattern).hasAnyRole()
                .antMatchers(authUrlPattern).hasAnyRole()
                .anyRequest().permitAll();
    }
}
