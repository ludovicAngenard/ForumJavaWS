package com.ForumJavaWS.demo.rest.controller;

import java.util.Date;
import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.repository.CategoryRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController { // READ ONLY

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TopicRepository topicRepository;

    @ResponseBody
    @GetMapping("/category")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/category/{categoryId}")
    public Category getCategoryById(final @PathVariable("categoryId") Long categoryId) {
        try {
            return categoryRepository.findById(categoryId);
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @GetMapping("/category/{categoryId}/topics")
    public Page<Topic> getTopicsByCategory(final @PathVariable("categoryId") Long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId);
        return topicRepository.findByCategoryOrderByTitle(category, pageable);
    }

    // il manque l'ID du topic et l'ID
    @PostMapping("/category/{categoryId}")
    public Topic addTopicToCategory(final @PathVariable("categoryId") Long id, final @RequestBody Topic topic) {
        Category category = categoryRepository.findById(id);
        if (topic.getTitle() != null) {
            category.getTopics().add(topic);
            topic.setLocked(false);
            topic.setCategory(category);
            topic.getPosts().forEach(post -> {
                Date date = new Date();
                post.setTopic(topic);
                post.setCreatedAt(date);
            });
            categoryRepository.save(category);

            return topic;
        } else {
            return null;
        }

    }

}
