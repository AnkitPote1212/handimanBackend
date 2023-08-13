package com.jwt.jwtAuthentication.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwt.jwtAuthentication.entity.HandimanUserEntity;
import com.jwt.jwtAuthentication.entity.UserPincodeEntity;

@Repository
public interface UserPincodeRepository extends JpaRepository<UserPincodeEntity, Long> {
	
	@Query("Select pc.pincode.postalCode from UserPincodeEntity pc where pc.user = :user")
	List<Long> findByUserId(HandimanUserEntity user);
	
	@Modifying
	@Transactional
    @Query("DELETE FROM UserPincodeEntity up WHERE up.user = :user")
    void deleteByUser(HandimanUserEntity user);
}
