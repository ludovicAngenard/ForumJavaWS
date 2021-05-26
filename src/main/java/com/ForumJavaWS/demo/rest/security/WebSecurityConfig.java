package com.ForumJavaWS.demo.rest.security;

import com.ForumJavaWS.demo.rest.security.jwt.AuthEntryPointJwt;
import com.ForumJavaWS.demo.rest.security.jwt.AuthTokenFilter;
import com.ForumJavaWS.demo.rest.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Cette fonction permet de gérer une grosse partie des requetes possible en
  // fonction du rôle de l'utilsateur connecté,
  // dans certains cas, ils sont présent dans le controller en question
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .antMatchers("/auth/**").permitAll().antMatchers(HttpMethod.GET, "/topic/**").permitAll()
        .antMatchers(HttpMethod.GET, "/post/**").permitAll()
        .antMatchers(HttpMethod.GET, "/category/**").permitAll()
        .antMatchers(HttpMethod.GET, "/report/**").access("hasRole('MODERATOR') or hasRole('ADMIN')")
        .antMatchers(HttpMethod.POST, "/post/**").access("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
        .antMatchers(HttpMethod.POST, "/topic/**").access("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
        .antMatchers(HttpMethod.POST, "/report/**").access("hasRole('MODERATOR') or hasRole('ADMIN')  or hasRole('USER')")
        .antMatchers(HttpMethod.PUT, "/post/**").access("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
        .antMatchers(HttpMethod.PUT, "/topic/**").access("hasRole('MODERATOR') or hasRole('ADMIN')")
        .antMatchers(HttpMethod.PUT, "/user/**").access("hasRole('MODERATOR') or hasRole('ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/topic/**").access("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
        .antMatchers(HttpMethod.DELETE, "/post/**").access("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')").anyRequest().authenticated();
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
