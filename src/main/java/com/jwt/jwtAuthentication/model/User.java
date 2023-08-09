package com.jwt.jwtAuthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="loginapp.user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private String password;
	private String email;
	private String role;
	private Long cityId;
	private boolean isActive;
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", role=" + role + ", cityId=" + cityId + ", isActive=" + isActive + "]";
	}
	public User(Long id, String firstName, String lastName, String password, String email, String role, Long cityId,
			boolean isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.cityId = cityId;
		this.isActive = isActive;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String firstName, String lastName, String password, String email, String role, Long cityId,
			boolean isActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.cityId = cityId;
		this.isActive = isActive;
	}
}
