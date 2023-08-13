package com.jwt.jwtAuthentication.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization","Content-Type","authorizationHeader"})
public class AdminController {
	
	@GetMapping("/test")
	public String getPostalCode(){	
		return "This is Admin api";
	}

}
