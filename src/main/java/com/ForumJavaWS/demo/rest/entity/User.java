package com.ForumJavaWS.demo.rest.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> role = new HashSet<>();

    private Boolean locked;

      /**
   * @return the id
   */
    public Long getId() {
        return id;
    }

      /**
   * @param id the id to set
   */
    public void setId(Long id) {
        this.id = id;
    }

      /**
   * @return the email
   */
    public String getEmail() {
        return email;
    }

      /**
   * @param email the email to set
   */
    public void setEmail(String email) {
        this.email = email;
    }

      /**
   * @return the password
   */
    public String getPassword() {
        return password;
    }

      /**
   * @param password the password to set
   */
    public void setPassword(String password) {
        this.password = password;
    }

      /**
   * @return the roles
   */
    public Set<Role> getRoles() {
        return role;
    }

    /**
   * @param roles the roles to set
   */
    public void setRoles(Set<Role> role) {
        this.role = role;
    }

      /**
   * @return the locked
   */
    public Boolean getLocked() {
        return locked;
    }

          /**
   * @param locked the locked to set
   */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
