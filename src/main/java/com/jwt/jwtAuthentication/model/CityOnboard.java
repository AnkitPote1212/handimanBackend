package com.jwt.jwtAuthentication.model;

import org.springframework.web.multipart.MultipartFile;

public class CityOnboard {

	private MultipartFile cityImage;
	
	private String cityName;
	
	private String pincodes;

	public CityOnboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CityOnboard(MultipartFile cityImage, String cityName, String pincodes) {
		super();
		this.cityImage = cityImage;
		this.cityName = cityName;
		this.pincodes = pincodes;
	}

	public MultipartFile getCityImage() {
		return cityImage;
	}

	public void setCityImage(MultipartFile cityImage) {
		this.cityImage = cityImage;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPincodes() {
		return pincodes;
	}

	public void setPincodes(String pincodes) {
		this.pincodes = pincodes;
	}
	
	
}
