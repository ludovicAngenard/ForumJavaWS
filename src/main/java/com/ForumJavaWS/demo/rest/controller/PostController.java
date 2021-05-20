package com.ForumJavaWS.demo.rest.controller;

import java.util.Date;
import java.util.Optional;

import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.payload.DTO.PostDTO;
import com.ForumJavaWS.demo.rest.repository.PostRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;

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
    private TopicRepository topicRepository;

    @ResponseBody
    @GetMapping("/post/{postId}")
    public Post getPostById(final @PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId);
        return post;
    }

    @PutMapping("/post/{id}")
    public Post editPost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
        Post post = this.postRepository.findById(id);
        Date date = new Date();
        post.setUpdatedAt(date);
        post.setContent(postDTO.getContent());
        post.setTopic(postDTO.getTopic());
        return this.postRepository.save(post);
    }

}
