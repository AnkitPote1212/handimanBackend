package com.jwt.jwtAuthentication.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HandimanPojo {
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String contactNumber;
	
	private String city;
	
	private String pincode;
	
	private String service;
	
	private String password;
	
	private MultipartFile imageUpload;
	
	private MultipartFile documentUpload;

	private String aboutMe;

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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(MultipartFile imageUpload) {
		this.imageUpload = imageUpload;
	}

	public MultipartFile getDocumentUpload() {
		return documentUpload;
	}

	public void setDocumentUpload(MultipartFile documentUpload) {
		this.documentUpload = documentUpload;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public HandimanPojo(String firstName, String lastName, String email, String contactNumber, String city,
			String pincode, String service, String password, MultipartFile imageUpload, MultipartFile documentUpload,
			String aboutMe) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.city = city;
		this.pincode = pincode;
		this.service = service;
		this.password = password;
		this.imageUpload = imageUpload;
		this.documentUpload = documentUpload;
		this.aboutMe = aboutMe;
	}

	public HandimanPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
