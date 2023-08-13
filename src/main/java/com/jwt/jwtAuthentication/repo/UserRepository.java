package com.jwt.jwtAuthentication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.jwtAuthentication.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT user FROM User user WHERE user.email = :email")
	User findByEmail(String email);

	@Modifying
	@Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.cityId = :cityId WHERE u.email = :email")
	void updateUser(@Param("email") String email, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("cityId") Long cityId);

}
