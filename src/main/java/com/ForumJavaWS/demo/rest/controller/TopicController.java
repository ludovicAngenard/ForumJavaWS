package com.ForumJavaWS.demo.rest.controller;

import java.util.Date;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.payload.response.MessageResponse;
import com.ForumJavaWS.demo.rest.repository.PostRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;
import com.ForumJavaWS.demo.rest.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private PostRepository postRepository;

    // Fonction permettant de récuperer un topic et ses posts
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

    // Fonction permettant de récupérer les posts d'un topic de façon paginée
    @GetMapping("/topic/{topicId}/posts")
    public Page<Post> getPostsByTopic(final @PathVariable("topicId") Long topicId, Pageable pageable) {
        Topic topic = topicRepository.findById(topicId);// PageRequest.of(0, 10)
        Page<Post> posts = postRepository.findByTopicOrderByCreatedAt(topic, pageable);
        return posts;
    }

    // Fonction permettant d'ajouter un post a un topic
    @PostMapping("/topic/{idTopic}")
    public ResponseEntity<?> addPostToTopic(@PathVariable("idTopic") Long idTopic, @RequestBody Post post) {
        Topic topic = topicRepository.findById(idTopic);
        if (!topic.getLocked()){
            topic.getPosts().add(post);
            topic.setUser(UserDetailsServiceImpl.getCurrentUser());
            Date date = new Date();
            post.setCreatedAt(date);
            post.setTopic(topic);
            post.setUser(UserDetailsServiceImpl.getCurrentUser());
            topicRepository.save(topic);
            return  ResponseEntity.ok(post);
        } else {
            return ResponseEntity.ok(new MessageResponse(ApiMessage.ERROR_TOPIC_LOCKED, "Le topic a été fermé par un modérateur ou un administrateur."));
        }

    }

    // Fonction permettant de supprimer un topic si c'est le créateur qui le fait et
    // qu'il n'y a qu'un seul post
    @DeleteMapping("/topic/{id}")
    public ResponseEntity<?> deleteTopic(final @PathVariable("id") Long topicId) {
        Topic topic = topicRepository.findById(topicId);
        Long creator = topic.getUser().getId();
        if (creator == UserDetailsServiceImpl.getCurrentUser().getId() && topic.getPosts().size() == 1) {
            Category parentCategory = topic.getCategory();
            parentCategory.getTopics().remove(topic);
            topicRepository.delete(topic);
            return ResponseEntity.ok("le topic est supprimé.");
        } else {
            return ResponseEntity.ok("Vous n'êtes pas le propriétaire ou il y'a trop de post sur ce topic");
        }
    }

    // Fonction permettant de modifier un topic
    @PutMapping("/topic/{id}")
    public Topic editTopic(@PathVariable("id") Long id) {
        Topic topic = this.topicRepository.findById(id);
        topic.setLocked(true);
        return this.topicRepository.save(topic);
    }
}