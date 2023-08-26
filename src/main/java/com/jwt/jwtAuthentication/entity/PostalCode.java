package com.jwt.jwtAuthentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="postal_code")
public class PostalCode {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
    
    private Long postalCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}

	public PostalCode(Long id, CityEntity city, Long postalCode) {
		super();
		this.id = id;
		this.city = city;
		this.postalCode = postalCode;
	}

	public PostalCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PostalCode [id=" + id + ", cityId=" + city.getCityId() + ", postalCode=" + postalCode + "]";
	}

	public PostalCode(CityEntity city, Long postalCode) {
		super();
		this.city = city;
		this.postalCode = postalCode;
	}
	
    
    
}
