package com.jwt.jwtAuthentication.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwt.jwtAuthentication.entity.CityEntity;
import com.jwt.jwtAuthentication.entity.PostalCode;

public interface PinCodeRepository extends JpaRepository<PostalCode, Long> {

	@Query("Select pc.postalCode from PostalCode pc where pc.city.cityId = :cityId")
	Optional<List<Long>> findByCityId(Long cityId);

	@Query("Select pc from PostalCode pc where pc.city = :city")
	List<PostalCode> findByCity(CityEntity city);
	
	@Query("Select pc from PostalCode pc where pc.postalCode = :postalCode")
	PostalCode findByPostalCode(Long postalCode);

}
