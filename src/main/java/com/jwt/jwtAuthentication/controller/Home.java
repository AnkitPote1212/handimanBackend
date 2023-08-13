package com.jwt.jwtAuthentication.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwtAuthentication.model.DummyUser;
import com.jwt.jwtAuthentication.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = {"Authorization","Origin"})
public class Home {
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/hello")
	public List<DummyUser> showWelcome() {
		return userService.getStore();
	}	
	@GetMapping("/currentUser")
	public String currentUser(Principal principal) {
		return principal.getName();
	}
}
