package com.company.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

  @EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;
  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth
    // .inMemoryAuthentication()
    // .withUser("user").password("password").roles("USER");
    auth
        .userDetailsService(userDetailsService);
        
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .anonymous().and()
    .csrf().disable()
    .authorizeRequests()
    
    .antMatchers("/auth/**").permitAll()
    
    .anyRequest().fullyAuthenticated()
    
    .and()
    .addFilterBefore(new StatelessLoginFilter("/auth", userDetailsService, authenticationManager(), 
        tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
    .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
    
  }

}
