package com.jwt.jwtAuthentication.model;

public class City {

	private Long cityId;
	private String cityName;
	private String cityImage;
	public City(Long cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public City(Long cityId, String cityName, String cityImage) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityImage = cityImage;
	}
	public String getCityImage() {
		return cityImage;
	}
	public void setCityImage(String cityImage) {
		this.cityImage = cityImage;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	
	
}
