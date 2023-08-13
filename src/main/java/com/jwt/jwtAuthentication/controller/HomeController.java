package com.jwt.jwtAuthentication.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwtAuthentication.model.City;
import com.jwt.jwtAuthentication.model.CityServiceDetails;
import com.jwt.jwtAuthentication.model.HandimanPojo;
import com.jwt.jwtAuthentication.model.UserPojo;
import com.jwt.jwtAuthentication.service.HandimanService;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = {"Authorization", "Origin","Content-Type"})
public class HomeController {
	
	@Autowired
	private HandimanService handimanService;
	
	@GetMapping("/getHomePageDetails")
	public ResponseEntity<List<City>> loadHomePage(){	
		return new ResponseEntity<>(handimanService.homePageDetails(),HttpStatus.OK);
	}
	@GetMapping("/getCityService")
	public ResponseEntity<CityServiceDetails> getCityDetails(){	
	    return new ResponseEntity<>(handimanService.getServiceDetails(),HttpStatus.OK);
	}
	@PostMapping("/saveUser")
	public ResponseEntity<String> saveUser(@RequestBody UserPojo user){	
		 handimanService.saveUser(user);
		 return new ResponseEntity<>("User registered",HttpStatus.OK);
	}
	@PostMapping("/savehandimanUser")
	public ResponseEntity<String> savehandimanUser(@ModelAttribute HandimanPojo handimanPojo) throws IOException{	
		System.out.println(handimanPojo); 
		handimanService.savehandimanUser(handimanPojo);
		return new ResponseEntity<>("User registered",HttpStatus.OK);
	}

}
