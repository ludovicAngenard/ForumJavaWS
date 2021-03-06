package com.ForumJavaWS.demo.rest.repository;

import com.ForumJavaWS.demo.rest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category findById(Long id);
}
