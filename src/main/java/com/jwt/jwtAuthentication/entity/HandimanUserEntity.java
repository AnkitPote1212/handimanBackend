package com.jwt.jwtAuthentication.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "handiman-user")
public class HandimanUserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	private String firstName;
	
	private String lastName;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
	
	private String email;
	
	private float experience;
	
	private long ContactNo;
	
	private String profileImgUrl;
	
	private String resumeUrl;
	
	private String aboutMe;
	
	private String password;
	
	private String role="HANDIMAN_USER";
	
	private Boolean active;
	
	private Boolean verified;
	
	private Long passCode;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "service_id")
	private ServiceEntity service;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public long getContactNo() {
		return ContactNo;
	}

	public void setContactNo(long contactNo) {
		ContactNo = contactNo;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ServiceEntity getService() {
		return service;
	}

	public void setService(ServiceEntity service) {
		this.service = service;
	}
	

	public Long getPassCode() {
		return passCode;
	}

	public void setPassCode(Long passCode) {
		this.passCode = passCode;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public HandimanUserEntity(Long userId, String firstName, String lastName, CityEntity city, String email,
			float experience, long contactNo, String profileImgUrl, String resumeUrl, String aboutMe, String password,
			ServiceEntity service) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.experience = experience;
		ContactNo = contactNo;
		this.profileImgUrl = profileImgUrl;
		this.resumeUrl = resumeUrl;
		this.aboutMe = aboutMe;
		this.password = password;
		this.service = service;
	}
	public HandimanUserEntity(String firstName, String lastName, CityEntity city, String email, float experience,
			long contactNo, String profileImgUrl, String resumeUrl, String aboutMe, String password,
			ServiceEntity service) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.experience = experience;
		ContactNo = contactNo;
		this.profileImgUrl = profileImgUrl;
		this.resumeUrl = resumeUrl;
		this.aboutMe = aboutMe;
		this.password = password;
		this.service = service;
	}
	
	public HandimanUserEntity(Long userId, String firstName, String lastName, CityEntity city, String email,
			float experience, long contactNo, String profileImgUrl, String resumeUrl, String aboutMe, String password,
			String role, ServiceEntity service) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.experience = experience;
		ContactNo = contactNo;
		this.profileImgUrl = profileImgUrl;
		this.resumeUrl = resumeUrl;
		this.aboutMe = aboutMe;
		this.password = password;
		this.role = role;
		this.service = service;
	}

	public String getRole() {
		return role;
	}

	public HandimanUserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public HandimanUserEntity(Long userId, String firstName, String lastName, CityEntity city, String email,
			float experience, long contactNo, String profileImgUrl, String resumeUrl, String aboutMe, String password,
			String role, Boolean active, ServiceEntity service) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.experience = experience;
		ContactNo = contactNo;
		this.profileImgUrl = profileImgUrl;
		this.resumeUrl = resumeUrl;
		this.aboutMe = aboutMe;
		this.password = password;
		this.role = role;
		this.active = active;
		this.service = service;
	}

	public HandimanUserEntity(Long userId, String firstName, String lastName, CityEntity city, String email,
			float experience, long contactNo, String profileImgUrl, String resumeUrl, String aboutMe, String password,
			String role, Boolean active, Boolean verified, ServiceEntity service) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.experience = experience;
		ContactNo = contactNo;
		this.profileImgUrl = profileImgUrl;
		this.resumeUrl = resumeUrl;
		this.aboutMe = aboutMe;
		this.password = password;
		this.role = role;
		this.active = active;
		this.verified = verified;
		this.service = service;
	}

	public HandimanUserEntity(Long userId, String firstName, String lastName, CityEntity city, String email,
			float experience, long contactNo, String profileImgUrl, String resumeUrl, String aboutMe, String password,
			String role, Boolean active, Boolean verified, Long passCode, ServiceEntity service) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.experience = experience;
		ContactNo = contactNo;
		this.profileImgUrl = profileImgUrl;
		this.resumeUrl = resumeUrl;
		this.aboutMe = aboutMe;
		this.password = password;
		this.role = role;
		this.active = active;
		this.verified = verified;
		this.passCode = passCode;
		this.service = service;
	}

	public HandimanUserEntity(String firstName, String lastName, CityEntity city, String email, float experience,
			long contactNo, String profileImgUrl, String resumeUrl, String aboutMe, String password, Boolean active,
			Boolean verified, Long passCode, ServiceEntity service) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.experience = experience;
		ContactNo = contactNo;
		this.profileImgUrl = profileImgUrl;
		this.resumeUrl = resumeUrl;
		this.aboutMe = aboutMe;
		this.password = password;
		this.active = active;
		this.verified = verified;
		this.passCode = passCode;
		this.service = service;
	}

	
	
}
