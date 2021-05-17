package com.ForumJavaWS.demo.rest.controller;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.User;
import com.ForumJavaWS.demo.rest.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @GetMapping("/users/{reportId}")
    public User getreportById(final @PathVariable("id") Long userId){
        User user = userRepository.findById(userId);
        return user;
    }

    @ResponseBody
    @GetMapping("/users")
    public List<User> getUsersById(){
        return userRepository.findAll();
    }

    @ResponseBody
    @PostMapping("/user")
    public List<User> addUserById(){
        return userRepository.findAll();
    }
}
