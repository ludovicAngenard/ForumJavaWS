package com.ForumJavaWS.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.ForumJavaWS.demo.rest.entity.Category;
import com.ForumJavaWS.demo.rest.entity.EnumRole;
import com.ForumJavaWS.demo.rest.entity.Role;
import com.ForumJavaWS.demo.rest.entity.User;
import com.ForumJavaWS.demo.rest.repository.CategoryRepository;
import com.ForumJavaWS.demo.rest.repository.RoleRepository;
import com.ForumJavaWS.demo.rest.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ForumJavaWsApplication {
	private final CategoryRepository categoryRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	public ForumJavaWsApplication(CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository){
		this.categoryRepository = categoryRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ForumJavaWsApplication.class, args);
	}


	@PostConstruct void init(){
		Role adminRole = roleRepository.save(new Role(EnumRole.ROLE_ADMIN));
		roleRepository.save(new Role(EnumRole.ROLE_USER));

		// Création d'un utilisateur spécial ayant le rôle d'Administrateur,
		// seule personne habilitée à créer par la suite des entités comme Artist et Album
		User admin = new User();
		admin.setEmail("ludovic.angenard@campus.academy");
		admin.setPassword(encoder.encode("123456"));
		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);
		admin.setRoles(roles);
		userRepository.save(admin);

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
