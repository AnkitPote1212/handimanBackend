package com.jwt.jwtAuthentication.model;

public class ServiceParameters {

	private String serviceName;
	
	private String cityName;
	
	private String pinCode;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public ServiceParameters(String serviceName, String cityName, String pinCode) {
		super();
		this.serviceName = serviceName;
		this.cityName = cityName;
		this.pinCode = pinCode;
	}

	public ServiceParameters() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
