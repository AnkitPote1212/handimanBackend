package com.jwt.jwtAuthentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.jwtAuthentication.entity.ServiceEntity;

public interface ServieRepository extends JpaRepository<ServiceEntity, Long> {

	@Query("SELECT service FROM ServiceEntity service where service.serviceName = :service")
	ServiceEntity findByCityService(@Param("service") String service);

}
