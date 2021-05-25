package com.ForumJavaWS.demo.rest.repository;

import java.util.Optional;

import com.ForumJavaWS.demo.rest.entity.EnumRole;
import com.ForumJavaWS.demo.rest.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByName(EnumRole name);
}