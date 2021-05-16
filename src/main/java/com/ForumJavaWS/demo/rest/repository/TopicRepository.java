package com.ForumJavaWS.demo.rest.repository;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    public List<Topic> findByCategoryOrderByTitle(Category category);
}
