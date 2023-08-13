package com.jwt.jwtAuthentication.controller;

import java.io.IOException;
import java.security.Principal;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.jwtAuthentication.entity.CityEntity;
import com.jwt.jwtAuthentication.entity.PostalCode;
import com.jwt.jwtAuthentication.model.City;
import com.jwt.jwtAuthentication.model.CityServiceDetails;
import com.jwt.jwtAuthentication.model.DummyUser;
import com.jwt.jwtAuthentication.model.HandimanInfo;
import com.jwt.jwtAuthentication.model.HandimanPojo;
import com.jwt.jwtAuthentication.model.JwtRequest;
import com.jwt.jwtAuthentication.model.ServiceParameters;
import com.jwt.jwtAuthentication.model.User;
import com.jwt.jwtAuthentication.model.UserPojo;
import com.jwt.jwtAuthentication.service.HandimanService;
import com.jwt.jwtAuthentication.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization","Content-Type","authorizationHeader"})
public class ServiceController {
	
	@Autowired
	private HandimanService handimanService;

	
	@PostMapping("/getHandimanlist")
	public ResponseEntity<List<HandimanInfo>> getHandimanlist(@RequestBody ServiceParameters serviceParameters){	
		 return new ResponseEntity<>(handimanService.getHandimanList(serviceParameters),HttpStatus.OK);
	}
	@GetMapping("/getPostalCode")
	public ResponseEntity<List<Long>> getPostalCode(@RequestParam String cityName){	
		return new ResponseEntity<>(handimanService.getPostalCodeByCityName(cityName),HttpStatus.OK);
	}
	@GetMapping("/getCites")
	public ResponseEntity<List<String>> getAllCites(){	
		return new ResponseEntity<>(handimanService.getAllCites(),HttpStatus.OK);
	}
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@ModelAttribute HandimanPojo handimanPojo){	
		System.out.println("tets");
		 return new ResponseEntity<>(handimanService.updateHandiman(handimanPojo),HttpStatus.OK);
	}
}
