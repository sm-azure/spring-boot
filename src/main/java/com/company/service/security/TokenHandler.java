package com.company.service.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

  private final String secret;
  private final UserDetailsService userDetailsService;
  
  public TokenHandler(String secret, UserDetailsService userDetailsService){
    this.secret = secret;
    this.userDetailsService = userDetailsService;
  }
  
  public User parseUserFromToken(String token){
    String username = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    User user =  (User) userDetailsService.loadUserByUsername(username);
    return user;
  }
  
  public String createToken(User user){
    return Jwts.builder()
        .setSubject(user.getUsername())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
  
}
