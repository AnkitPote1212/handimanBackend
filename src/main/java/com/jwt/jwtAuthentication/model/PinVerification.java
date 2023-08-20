package com.jwt.jwtAuthentication.model;

public class PinVerification {
	private String email;
	
	private String pin;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public PinVerification(String email, String pin) {
		super();
		this.email = email;
		this.pin = pin;
	}

	public PinVerification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
