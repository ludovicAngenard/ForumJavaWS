package com.ForumJavaWS.demo.rest.controller;

import com.ForumJavaWS.demo.rest.entity.User;
import com.ForumJavaWS.demo.rest.repository.UserRepository;
import com.ForumJavaWS.demo.rest.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

     // Fonction permettant de bloquer un user
     @PutMapping("/user/{userId}")
     public  ResponseEntity<?> editUser(@PathVariable("userId") Long id) {
        User user = userRepository.findById(id);
        if ( UserDetailsServiceImpl.getCurrentUser().getId() != user.getId()){
            user.setLocked(true);
            return  ResponseEntity.ok(userRepository.save(user));
        } else {
            return  ResponseEntity.ok("Vous n'avez pas les droits pour modifier un user.");
        }

     }
}
