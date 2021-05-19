package com.ForumJavaWS.demo.rest.repository;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findByTopicOrderById(Topic topic);

    public Post findById(Long id);

    public List<Post> findByTopicOrderByCreatedAt(Topic topic);

    public Topic findTopicById(Long id);
}
