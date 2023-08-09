package com.jwt.jwtAuthentication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwt.jwtAuthentication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT user FROM User user WHERE user.email = :email")
	User findByEmail(String email);
}
