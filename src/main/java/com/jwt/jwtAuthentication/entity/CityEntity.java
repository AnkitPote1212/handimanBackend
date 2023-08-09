package com.jwt.jwtAuthentication.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="city")
public class CityEntity implements Comparable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cityId;
	
	private String cityName;
	
	private String cityImage;
	
	public CityEntity(Long cityId, String cityName, String cityImage, List<HandimanUserEntity> users,
			List<PostalCode> postalCodes) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityImage = cityImage;
		this.users = users;
		this.postalCodes = postalCodes;
	}

	public String getCityImage() {
		return cityImage;
	}

	public void setCityImage(String cityImage) {
		this.cityImage = cityImage;
	}
	
	@OneToMany(mappedBy = "city")
	private List<HandimanUserEntity> users;
	 
	@OneToMany(mappedBy = "city")
	private List<PostalCode> postalCodes;
	
	 public CityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CityEntity(Long cityId, String cityName, List<HandimanUserEntity> users, List<PostalCode> postalCodes) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.users = users;
		this.postalCodes = postalCodes;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<HandimanUserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<HandimanUserEntity> users) {
		this.users = users;
	}

	public List<PostalCode> getPostalCodes() {
		return postalCodes;
	}

	public void setPostalCodes(List<PostalCode> postalCodes) {
		this.postalCodes = postalCodes;
	}

	public CityEntity(String cityName, List<HandimanUserEntity> users, List<PostalCode> postalCodes) {
		super();
		this.cityName = cityName;
		this.users = users;
		this.postalCodes = postalCodes;
	}

	@Override
	public String toString() {
		return "CityEntity [cityId=" + cityId + ", cityName=" + cityName + "]";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
