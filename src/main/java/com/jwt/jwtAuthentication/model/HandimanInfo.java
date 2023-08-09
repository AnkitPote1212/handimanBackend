package com.jwt.jwtAuthentication.model;

import java.util.List;

public class HandimanInfo {
	
	private String firstName;
	
	private String lastName;
	
	private String city;
	
	private String serviceProvided;
	
	private float experiance;
	
	private Long contactNumber;
	
	private String email;
	
	private String profileImage;
	
	private String aboutMe;
	
	private List<Long> postalCode;
	
	private String role;
	
	private String resumeUrl;
	
	
	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Long> getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(List<Long> postalCode) {
		this.postalCode = postalCode;
	}

	public HandimanInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HandimanInfo(String firstName, String lastName, String city, String serviceProvided, float experiance,
			Long contactNumber, String email, String profileImage, String aboutMe) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.serviceProvided = serviceProvided;
		this.experiance = experiance;
		this.contactNumber = contactNumber;
		this.email = email;
		this.profileImage = profileImage;
		this.aboutMe = aboutMe;
	}

	public HandimanInfo(String firstName, String lastName, String city, String serviceProvided, float experiance,
			Long contactNumber, String email, String profileImage, String aboutMe, List<Long> postalCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.serviceProvided = serviceProvided;
		this.experiance = experiance;
		this.contactNumber = contactNumber;
		this.email = email;
		this.profileImage = profileImage;
		this.aboutMe = aboutMe;
		this.postalCode = postalCode;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getServiceProvided() {
		return serviceProvided;
	}

	public void setServiceProvided(String serviceProvided) {
		this.serviceProvided = serviceProvided;
	}

	public float getExperiance() {
		return experiance;
	}

	public void setExperiance(float experiance) {
		this.experiance = experiance;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public HandimanInfo(String firstName, String lastName, String city, String serviceProvided, float experiance,
			Long contactNumber, String email, String profileImage, String aboutMe, List<Long> postalCode, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.serviceProvided = serviceProvided;
		this.experiance = experiance;
		this.contactNumber = contactNumber;
		this.email = email;
		this.profileImage = profileImage;
		this.aboutMe = aboutMe;
		this.postalCode = postalCode;
		this.role = role;
	}

	public HandimanInfo(String firstName, String lastName, String city, String serviceProvided, float experiance,
			Long contactNumber, String email, String profileImage, String aboutMe, List<Long> postalCode, String role,
			String resumeUrl) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.serviceProvided = serviceProvided;
		this.experiance = experiance;
		this.contactNumber = contactNumber;
		this.email = email;
		this.profileImage = profileImage;
		this.aboutMe = aboutMe;
		this.postalCode = postalCode;
		this.role = role;
		this.resumeUrl = resumeUrl;
	}
	
	
}
