package com.ForumJavaWS.demo.rest.controller;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.repository.CategoryRepository;

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

    @ResponseBody
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/categories/{id}")
    public Category getCategoryById(final @PathVariable("id") Integer categoryId){
        try{
            return categoryRepository.findById(categoryId).get();
        } catch (Exception e) {
            return null;
        }
    }
}
