package com.company.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
  
  private static Map<String, User> userRepository = new HashMap<String, User>();
  
  
  static{
    Collection<GrantedAuthority> adminAuthority = new ArrayList<GrantedAuthority>();
    Collection<GrantedAuthority> userAuthority = new ArrayList<GrantedAuthority>();
    
    adminAuthority.add(new SimpleGrantedAuthority("ADMIN"));
    userAuthority.add(new SimpleGrantedAuthority("USER"));
    
    userRepository.put("admin", new User("admin", "adminpassword", true, true, true, true, adminAuthority));
    userRepository.put("user", new User("user", "userpassword", true, true, true, true, userAuthority));
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.get(username);
    if(user == null)
      throw new UsernameNotFoundException("Could not find username: " + username);
    
    
    UserDetails userDetails = new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    
    logger.info(user.getUsername()+":"+user.getPassword());
    return userDetails;
  }

 

}
