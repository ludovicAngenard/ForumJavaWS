package com.ForumJavaWS.demo.rest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ForumJavaWS.demo.rest.entity.EnumRole;
import com.ForumJavaWS.demo.rest.entity.Role;
import com.ForumJavaWS.demo.rest.entity.User;
import com.ForumJavaWS.demo.rest.payload.request.LoginRequest;
import com.ForumJavaWS.demo.rest.payload.request.SignupRequest;
import com.ForumJavaWS.demo.rest.payload.response.JwtResponse;
import com.ForumJavaWS.demo.rest.payload.response.MessageResponse;
import com.ForumJavaWS.demo.rest.repository.RoleRepository;
import com.ForumJavaWS.demo.rest.repository.UserRepository;
import com.ForumJavaWS.demo.rest.security.jwt.JwtUtils;
import com.ForumJavaWS.demo.rest.security.service.UserDetailsImpl;
import com.ForumJavaWS.demo.rest.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

      return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
    } catch (AuthenticationException e) {
      return ResponseEntity.ok(new MessageResponse(ApiMessage.ERROR_LOGIN_FAILED, "Identifiants invalides"));
    }
  }

  @PostMapping("register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.ok()
          .body(new MessageResponse(ApiMessage.ERROR_REGISTER_EMAIL_TAKEN, "L'adresse email est déjà prise"));
    }

    User user = new User();
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(encoder.encode(signUpRequest.getPassword()));

    Set<Role> roles = new HashSet<Role>();
    Optional<Role> optional = roleRepository.findByName(EnumRole.ROLE_USER);
    roles.add(optional.get());
    user.setRoles(roles);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse(ApiMessage.REGISTER_OK, "Utilisateur inscrit avec succès !"));
  }

  @PostMapping("register/moderator")
  public ResponseEntity<?> adminAddModerator(@Valid @RequestBody SignupRequest signUpRequest) {
    if (UserDetailsServiceImpl.isAdmin()) {
      return ResponseEntity.ok(new MessageResponse(ApiMessage.REGISTER_OK, "Modérateur inscrit avec succès !"));
    } else {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Sorry but no!");
    }
  }

}