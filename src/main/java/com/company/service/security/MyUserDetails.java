package com.company.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = 1607244082891882012L;


  public MyUserDetails(Collection<GrantedAuthority> authorities, String userName, String password) {
    this.authorities = authorities;
    this.userName = userName;
    this.password = password;
  }

  private Collection<GrantedAuthority> authorities;
  private String userName;
  private String password;

  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return authorities;
  }

  @Override
  public String getPassword() {
   return password;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

}
