package com.ForumJavaWS.demo.rest.repository;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    public Page<Topic> findByCategoryOrderByTitle(Category category, Pageable pageable);
    public Topic findById(Long id);
    public void deleteById(Long id);
    public List<Topic>findByCategory(Category category);
}
