package com.company.service.security;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

  private UserDetailsService userService;
  private AuthenticationManager authManager;
  private TokenAuthenticationService tokenAuthenticationService;

  public StatelessLoginFilter(String defaultFilterProcessesUrl, UserDetailsService userService,
      AuthenticationManager authManager, TokenAuthenticationService tokenAuthenticationService) {
    super(defaultFilterProcessesUrl);

    this.userService = userService;
    this.authManager = authManager;
    this.tokenAuthenticationService = tokenAuthenticationService;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

    try {
      User userDetails = getUserDetailsFromJSON(request);
      Authentication auth =
          authManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
              userDetails.getPassword()));

      return auth;

    } catch (Exception e) {
       throw new AuthenticationException("Unknown user") {};
    }

  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {

    // lookup user and create authentication
    final User authenticatedUser = (User) userService.loadUserByUsername(authResult.getName());
    final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

    // add custom token as HTTP header
    tokenAuthenticationService.addAuthentication(response, userAuthentication);

    // Add authentication to the security context
    SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    chain.doFilter(request, response);
    SecurityContextHolder.getContext().setAuthentication(null);
  }


  private User getUserDetailsFromJSON(HttpServletRequest request) {

    StringBuffer jb = new StringBuffer();
    String line = null;
    try {
      BufferedReader reader = request.getReader();
      while ((line = reader.readLine()) != null)
        jb.append(line);
    } catch (Exception e) { /* report an error */
    }


    JSONObject jsonObject = new JSONObject(jb.toString());


    return new User(jsonObject.getString("username"), jsonObject.getString("password"), new ArrayList<GrantedAuthority>());
  }



}
