package com.ForumJavaWS.demo.rest.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private Long id;
  private String username;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.roles = roles;
  }

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}