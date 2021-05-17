package com.ForumJavaWS.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.repository.CategoryRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumJavaWsApplication {

	private final CategoryRepository categoryRepository;

	public ForumJavaWsApplication(CategoryRepository categoryRepository){
		this.categoryRepository = categoryRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ForumJavaWsApplication.class, args);
	}

	@PostConstruct void init(){
		List<String> themes = new ArrayList<>(Arrays.asList("Mathématiques", "Informatique", "Astronomie", "Ingénierie"));
		if (categoryRepository.findAll().size() <= 3){
			themes.forEach(theme -> {
				Category newCategory = new Category();
				newCategory.setName(theme);
				categoryRepository.save(newCategory);
			});
		}
	}
}
