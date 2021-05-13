package com.ForumJavaWS.demo.rest.repository;

import com.ForumJavaWS.demo.rest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
