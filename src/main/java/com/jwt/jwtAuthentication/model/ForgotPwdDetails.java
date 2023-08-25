package com.jwt.jwtAuthentication.model;

public class ForgotPwdDetails {
	
	private String registeredEmail;
	
    private String pin;
    
    private String password;

	public ForgotPwdDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPwdDetails(String registeredEmail, String pin, String password) {
		super();
		this.registeredEmail = registeredEmail;
		this.pin = pin;
		this.password = password;
	}

	public String getRegisteredEmail() {
		return registeredEmail;
	}

	public void setRegisteredEmail(String registeredEmail) {
		this.registeredEmail = registeredEmail;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
	
}
