package com.ForumJavaWS.demo.rest.repository;

import java.util.Optional;

import com.ForumJavaWS.demo.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findById(Long id);
    public Optional<User> findByEmail(String email);
    public Boolean existsByEmail(String email);
}
