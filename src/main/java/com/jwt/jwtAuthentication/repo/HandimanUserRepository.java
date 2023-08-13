package com.jwt.jwtAuthentication.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jwt.jwtAuthentication.entity.HandimanUserEntity;
import com.jwt.jwtAuthentication.model.User;

@Repository
@Transactional
public interface HandimanUserRepository extends JpaRepository<HandimanUserEntity, Long> {
	
	@Query("SELECT u FROM HandimanUserEntity u " +
		       "JOIN u.city c " +
		       "JOIN u.service s " +
		       "JOIN UserPincodeEntity up ON up.user = u " +
		       "WHERE c.cityName = :cityName " +
		       "AND s.serviceName = :serviceName " +
		       "AND up.pincode.postalCode = :postalCode")
		Optional<List<HandimanUserEntity>> findHandiman(@Param("cityName") String cityName, 
		                                                @Param("serviceName") String serviceName, 
		                                                @Param("postalCode") Long postalCode);
	
	@Query("SELECT handimanUser FROM HandimanUserEntity handimanUser WHERE handimanUser.email = :email")
	HandimanUserEntity findByEmail(String email);


}
