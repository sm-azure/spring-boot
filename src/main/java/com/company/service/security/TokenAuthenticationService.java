package com.company.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenAuthenticationService {
  
  private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
  
  private TokenHandler tokenHandler;
  private final String secret = "mysecret";
  
  @Autowired
  public TokenAuthenticationService(UserDetailsService userDetailsService)
  {
    tokenHandler = new TokenHandler(secret, userDetailsService);
  }

  public void addAuthentication(HttpServletResponse response, UserAuthentication authentication){
    final User user = (User) authentication.getDetails();
    response.addHeader(AUTH_HEADER_NAME, tokenHandler.createToken(user));
  }
  
  public Authentication getAuthentication(HttpServletRequest request){
    final String token = request.getHeader(AUTH_HEADER_NAME);
    if(token!=null){
      final User user = tokenHandler.parseUserFromToken(token);
      if(user!=null){
        return new UserAuthentication(user);
      }
    }
    return null;
  }
}
