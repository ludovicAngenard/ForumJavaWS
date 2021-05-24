package com.ForumJavaWS.demo.rest.controller;

import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Report;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.payload.response.PostResponse;
import com.ForumJavaWS.demo.rest.repository.PostRepository;
import com.ForumJavaWS.demo.rest.repository.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReportRepository reportRepository;

    @ResponseBody
    @GetMapping("/post/{postId}")
    public Post getPostById(final @PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId);
        return post;
    }

    @DeleteMapping("/post/{postId}")
    public void deletePostById(final @PathVariable("postId") Long postId){
        Post post = postRepository.findById(postId);
        Topic containerTopic = post.getTopic();
        if (post.getId() != containerTopic.getPosts().get(0).getId()){
            containerTopic.getPosts().remove(post);
            postRepository.delete(post);
        }
    }

    @PutMapping("/post/{id}")
    public Post editPost(@PathVariable("id") Long id, @RequestBody PostResponse postResponse) {
        Post post = this.postRepository.findById(id);
        post.setUpdatedAt(postResponse.getUpdatedAt());
        post.setContent(postResponse.getContent());
        post.setTopic(postResponse.getTopic());
        return this.postRepository.save(post);
    }

    @GetMapping("/post/{postId}/Reports")
    public Page<Report> getReportsByPost(final @PathVariable("postId") Long postId, Pageable pageable){
        Post post = postRepository.findById(postId);
        return reportRepository.findByPostOrderById(post, pageable);
    }
}
