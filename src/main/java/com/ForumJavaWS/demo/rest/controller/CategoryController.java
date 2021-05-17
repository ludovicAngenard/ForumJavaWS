package com.ForumJavaWS.demo.rest.controller;

import java.util.ArrayList;
import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Topic;
import com.ForumJavaWS.demo.rest.repository.CategoryRepository;
import com.ForumJavaWS.demo.rest.repository.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    // READ ONLY
    @Autowired
    private CategoryRepository categoryRepository;
    private TopicRepository topicRepository;

    @ResponseBody
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/categories/{id}")
    public Category getCategoryById(final @PathVariable("id") Long categoryId){
        try{
            return categoryRepository.findById(categoryId);
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @GetMapping("/categories/{categoryId}/topics")
    public List<Topic> getTopicsByCategory(final Category category){
        try {
            System.out.println(category);
            List<Topic> topics = topicRepository.findByCategory(category);
            System.out.println("Topics : " + topics);
            return topics;

        } catch(Exception e) {
            return new ArrayList<Topic>();
        }
    }
}
