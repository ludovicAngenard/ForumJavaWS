package com.ForumJavaWS.demo.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.payload.DTO.TopicDTO;
import com.ForumJavaWS.demo.rest.repository.PostRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TopicController {
    @Autowired
    private TopicRepository topicRepository;
    private PostRepository postRepository;
    @ResponseBody
    @GetMapping("/topic/{topicId}")
    public Topic getTopicById(final @PathVariable("topicId") Long topicId) {
        try {
            Topic topic = topicRepository.findById(topicId);
            return topic;
        } catch (Exception e) {
            return null;
        }

    }


    // @GetMapping("/topic/{topicId}/post")
    // public Page<Post> getPostsByTopic(final @PathVariable("topicId") Long topicId, Pageable pageable) {
    //     Topic topic = topicRepository.findById(topicId);
    //     Page<Post> posts =  postRepository.findByTopicOrderByCreatedAt(topic, PageRequest.of(0, 10));
    //     return posts;
    // }

    @PostMapping("/topic/{idTopic}")
    public Post addPostToTopic(@PathVariable("idTopic") Long idTopic, @RequestBody Post post) {
        Topic topic = topicRepository.findById(idTopic);
        topic.getPosts().add(post);
        Date date = new Date();
        post.setCreatedAt(date);
        post.setTopic(topic);
        topicRepository.save(topic);
        return post;
    }
    // TODO
    // ONLY HIS CREATOR CAN DELETE THE TOPIC IF THERE IS ONLY ONE POST
    @DeleteMapping("/topic/{id}")
    public void deleteTopic(final @PathVariable("id") Long topicId) {
        Topic topic = topicRepository.findById(topicId);
        Category parentCategory = topic.getCategory();
        parentCategory.getTopics().remove(topic);
        topicRepository.delete(topic);
    }

    // TODO
    // EDITED ONLY BY A MODERATOR OR AN ADMIN
    @PutMapping("/topic/{id}")
    public Topic editTopic(@PathVariable("id") Long id, @RequestBody TopicDTO topicDTO) {
        Topic topic = this.topicRepository.findById(id);
        topic.setLocked(topicDTO.getLocked());
        return this.topicRepository.save(topic);
    }
}
