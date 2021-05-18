package com.ForumJavaWS.demo.rest.controller;

import java.util.Date;

import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.payload.DTO.PostDTO;
import com.ForumJavaWS.demo.rest.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PostController {
    @Autowired
    private PostRepository postRepository;

    @ResponseBody
    @GetMapping("/post/{postId}")
    public Post getPostById(final @PathVariable("postId") Long postId){
        Post post = postRepository.findById(postId);
        return post;
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        Date date = new Date();
        post.setCreatedAt(date);
        return postRepository.save(post);
    }

    @PutMapping("/post/{id}")
    public Post editTopic(@PathVariable("id") Long id, @RequestBody PostDTO postDTO ){
        Post post = this.postRepository.findById(id);
        post.setUpdatedAt(postDTO.getUpdatedAt());
        post.setContent(postDTO.getContent());
        post.setTopic(postDTO.getTopic());
        return this.postRepository.save(post);
    }

}
