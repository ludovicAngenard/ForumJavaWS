package com.ForumJavaWS.demo.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.payload.DTO.TopicDTO;
import com.ForumJavaWS.demo.rest.repository.PostRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/topic/{id}")
    public Topic getTopicById(final @PathVariable("id") Long topicId) {
        try {
            Topic topic = topicRepository.findById(topicId);
            return topic;
        } catch (Exception e) {
            return null;
        }

    }

    @ResponseBody
    @GetMapping("/topic/{topicId}/Post")
    public List<Post> getPostsByTopic(final Topic topic) {
        try {
            List<Post> posts = postRepository.findByTopicOrderByCreatedAt(topic);
            return posts;
        } catch (Exception e) {
            return new ArrayList<Post>();
        }
    }

    @PostMapping("/topic/{idTopic}")
    public Post addPostToTopic(@PathVariable("idTopic") Long idTopic, @RequestBody Post post) {
        Topic topic = topicRepository.findById(idTopic);
        topic.getPosts().add(post);
        Date date = new Date();
        post.setCreatedAt(date);
        return postRepository.save(post);
    }

    @PostMapping("/topic")
    public Topic addTopic(@RequestBody Topic topic) {
        // TODO
        // CHECK IF HE HAS A TITLE
        // CREATE A POST
        return topicRepository.save(topic);
    }

    // TODO
    // ONLY HIS CREATOR CAN DELETE THE TOPIC IF THERE IS ONLY ONE POST
    // @Transactional // is used for indicating a method run inside a database
    // transaction.
    @DeleteMapping("/topic/{id}")
    public void deleteTopic(final @PathVariable("id") Long topicId) {
        if (topicRepository.findById(topicId).getPosts().size() == 1) {
            topicRepository.deleteById(topicId);
        }
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
