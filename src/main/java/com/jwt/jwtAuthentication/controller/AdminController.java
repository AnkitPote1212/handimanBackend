package com.jwt.jwtAuthentication.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwtAuthentication.model.CityOnboard;
import com.jwt.jwtAuthentication.model.HandimanInfo;
import com.jwt.jwtAuthentication.model.HandimanPojo;
import com.jwt.jwtAuthentication.model.PinVerification;
import com.jwt.jwtAuthentication.model.ServiceParameters;
import com.jwt.jwtAuthentication.service.HandimanService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization","Content-Type","authorizationHeader"})
public class AdminController {
	
	@Autowired
	private HandimanService handimanService;
	
	@GetMapping("/test")
	public String getPostalCode(){	
		return "This is Admin api";
	}
	@GetMapping("/getUnApprovedList")
	public ResponseEntity<List<HandimanInfo>> getHandimanlist(){	
		 return new ResponseEntity<>(handimanService.getUnApprovedHandimanList(),HttpStatus.OK);
	}
	@PostMapping("/approve")
	public ResponseEntity<Map<String,String>> approveHandiman(@RequestBody Map<String,String>  user){
		Map<String,String> map=new HashMap<>();
		if(handimanService.approveUser(user.get("email"))) {
			map.put("successMsg","user Approved");
			 return new ResponseEntity<>(map,HttpStatus.OK);
		}else {
			 map.put("errorMsg","Server Side Error");
			 return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
}
