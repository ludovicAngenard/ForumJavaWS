package com.ForumJavaWS.demo.rest.security.service;

import com.ForumJavaWS.demo.rest.entity.EnumRole;
import com.ForumJavaWS.demo.rest.entity.User;
import com.ForumJavaWS.demo.rest.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
    return UserDetailsImpl.build(user);
  }

  /**
   * @return
   */
  public static User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    User user = new User();
    user.setId(userDetails.getId());
    return user;
  }

  /**
   * @return
   */
  public static boolean isAdmin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getAuthorities().stream().filter(r -> r.getAuthority().equals(EnumRole.ROLE_ANONYMOUS.toString())).count() == 1) {
      return false;
    }

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    GrantedAuthority grantedAuthority = userDetails.getAuthorities().stream().filter(r -> r.getAuthority().equals(EnumRole.ROLE_ADMIN.toString())).findFirst().orElse(null);
    return grantedAuthority != null;
  }

}