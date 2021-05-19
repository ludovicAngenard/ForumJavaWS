package com.ForumJavaWS.demo.rest.controller;

import java.util.ArrayList;
import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.repository.CategoryRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    // READ ONLY
    @Autowired
    private CategoryRepository categoryRepository;

    @ResponseBody
    @GetMapping("/category")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/category/{id}")
    public Category getCategoryById(final @PathVariable("id") Long categoryId) {
        try {
            return categoryRepository.findById(categoryId);
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @GetMapping("/category/{categoryId}/topic")
    public List<Topic> getTopicsByCategory(final Category category) {
        try {
            System.out.println(category);
            List<Topic> topics = category.getTopics();
            System.out.println("Topics : " + topics);
            return topics;

        } catch (Exception e) {
            return new ArrayList<Topic>();
        }
    }

    @PostMapping("/category/{categoryId}")
    @ResponseBody
    public ResponseEntity<Category> addTopicToCategory(final @PathVariable("categoryId") Long id,
            final @RequestBody Topic topic) {
        // System.out.println(topic.getTitle());
        System.out.println(topic.getId());
        Category category = categoryRepository.findById(id);
        category.getTopics().add(topic);
        // Post newPost = new Post();
        // newPost.setContent("jesuis un autre test");
        // topic.getPosts().add(newPost);
        categoryRepository.save(category);

        return ResponseEntity.ok(category);

    }

}
