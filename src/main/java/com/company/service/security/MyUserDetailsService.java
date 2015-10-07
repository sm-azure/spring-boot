package com.company.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
  
  private static Map<String, UserDetails> userRepository = new HashMap<String, UserDetails>();
  
  
  static{
    Collection<GrantedAuthority> adminAuthority = new ArrayList<GrantedAuthority>();
    Collection<GrantedAuthority> userAuthority = new ArrayList<GrantedAuthority>();
    
    adminAuthority.add(new SimpleGrantedAuthority("ADMIN"));
    userAuthority.add(new SimpleGrantedAuthority("USER"));
    
    userRepository.put("admin", new MyUserDetails(adminAuthority, "admin", "adminpassword"));
    userRepository.put("user", new MyUserDetails(userAuthority, "user", "userpassword"));
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = userRepository.get(username);
    if(user == null)
      throw new UsernameNotFoundException("Could not find username: " + username);
    
    
    
    logger.info(user.getUsername()+":"+user.getPassword());
    return user;
  }

 

}
