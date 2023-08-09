package com.jwt.jwtAuthentication.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwt.jwtAuthentication.entity.HandimanUserEntity;
import com.jwt.jwtAuthentication.entity.UserPincodeEntity;

public interface UserPincodeRepository extends JpaRepository<UserPincodeEntity, Long> {
	
	@Query("Select pc.pincode.postalCode from UserPincodeEntity pc where pc.user = :user")
	List<Long> findByUserId(HandimanUserEntity user);
}
