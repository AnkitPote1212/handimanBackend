package com.jwt.jwtAuthentication.model;

import java.util.List;
import java.util.Map;

import com.jwt.jwtAuthentication.entity.CityEntity;
import com.jwt.jwtAuthentication.entity.PostalCode;
import com.jwt.jwtAuthentication.entity.ServiceEntity;

public class CityServiceDetails {
	Map<String,List<Long>> cityPincodeMap;
	List<ServiceEntity> serviceList;
	
	public CityServiceDetails(Map<String, List<Long>> cityPincodeMap, List<ServiceEntity> serviceList) {
		super();
		this.cityPincodeMap = cityPincodeMap;
		this.serviceList = serviceList;
	}

	public CityServiceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map<String, List<Long>> getCityPincodeMap() {
		return cityPincodeMap;
	}

	public void setCityPincodeMap(Map<String, List<Long>> cityPincodeMap) {
		this.cityPincodeMap = cityPincodeMap;
	}

	public List<ServiceEntity> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceEntity> serviceList) {
		this.serviceList = serviceList;
	}
	
	
	
	
}
