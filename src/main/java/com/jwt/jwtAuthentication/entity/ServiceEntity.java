package com.jwt.jwtAuthentication.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="service")
public class ServiceEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long service_id;
	
	private String serviceName;
	
	 @JsonIgnore
	 @OneToMany(mappedBy = "service")
	 private List<HandimanUserEntity> users;

	public Long getService_id() {
		return service_id;
	}

	public void setService_id(Long service_id) {
		this.service_id = service_id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<HandimanUserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<HandimanUserEntity> users) {
		this.users = users;
	}

	public ServiceEntity(Long service_id, String serviceName, List<HandimanUserEntity> users) {
		super();
		this.service_id = service_id;
		this.serviceName = serviceName;
		this.users = users;
	}

	public ServiceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceEntity(String serviceName, List<HandimanUserEntity> users) {
		super();
		this.serviceName = serviceName;
		this.users = users;
	}

	@Override
	public String toString() {
		return "ServiceEntity [service_id=" + service_id + ", serviceName=" + serviceName + "]";
	}
}
