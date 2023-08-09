package com.jwt.jwtAuthentication.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.jwtAuthentication.entity.CityEntity;
import com.jwt.jwtAuthentication.entity.HandimanUserEntity;

public interface CityRepository extends JpaRepository<CityEntity,Long> {

	@Query("SELECT city.cityId FROM CityEntity city where city.cityName = :cityName")
	Optional<Long> findByCityName(@Param("cityName") String city);
	
	@Query("SELECT city.cityName FROM CityEntity city")
	List<String> findAllCities();
	
	@Query("SELECT city FROM CityEntity city where city.cityName = :cityName")
	CityEntity findByCityNameForCity(@Param("cityName") String city);
}
