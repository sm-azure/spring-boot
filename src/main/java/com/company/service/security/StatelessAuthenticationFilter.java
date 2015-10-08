package com.company.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class StatelessAuthenticationFilter extends GenericFilterBean {

  private TokenAuthenticationService tokenAuthenticationService;
  
  public StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService){
    this.tokenAuthenticationService = tokenAuthenticationService;
  }
  
  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    SecurityContextHolder.getContext().setAuthentication(
        tokenAuthenticationService.getAuthentication((HttpServletRequest)request));
    chain.doFilter(request, response);
  }

}
