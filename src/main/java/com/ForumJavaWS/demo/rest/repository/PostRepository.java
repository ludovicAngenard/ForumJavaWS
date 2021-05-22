package com.ForumJavaWS.demo.rest.repository;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Post;
import com.ForumJavaWS.demo.rest.entity.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findByTopicOrderById(Topic topic);

    public Post findById(Long id);

    public Page<Post> findByTopicOrderByCreatedAt(Topic topic, Pageable pageable);

    public Topic findTopicById(Long id);
}
