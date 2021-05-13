package com.ForumJavaWS.demo.rest.repository;

import com.ForumJavaWS.demo.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
