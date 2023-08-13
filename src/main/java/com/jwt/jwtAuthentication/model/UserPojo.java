package com.jwt.jwtAuthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPojo {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String city;
	
	@JsonIgnore
	private String password;
	
	private String role;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserPojo(String firstName, String lastName, String email, String city, String password, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.password = password;
		this.role = role;
	}

	public UserPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
