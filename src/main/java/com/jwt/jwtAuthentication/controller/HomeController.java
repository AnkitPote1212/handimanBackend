package com.jwt.jwtAuthentication.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jwt.jwtAuthentication.model.ForgotPwdDetails;
import com.jwt.jwtAuthentication.model.HandimanPojo;
import com.jwt.jwtAuthentication.model.PinVerification;
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
	public ResponseEntity<Map<String,String>> saveUser(@RequestBody UserPojo user){
		 Map<String,String> map=new HashMap<>();
		 if(handimanService.saveUser(user)) {
			 map.put("successMsg","User registered");
		 return new ResponseEntity<>(map,HttpStatus.OK);
		 }else {
			 map.put("errorMsg","user already exist");
			 return new ResponseEntity<>(map,HttpStatus.FORBIDDEN);
		 }
	}
	@PostMapping("/savehandimanUser")
	public ResponseEntity<Map<String,String>> savehandimanUser(@ModelAttribute HandimanPojo handimanPojo) throws IOException{	
		Map<String,String> map=new HashMap<>();
		if(handimanService.savehandimanUser(handimanPojo)) {
			map.put("successMsg","User registered");
			 return new ResponseEntity<>(map,HttpStatus.OK);
		}else {
			map.put("errorMsg","user already exist");
			 return new ResponseEntity<>(map,HttpStatus.FORBIDDEN);
		}
	}
	@PostMapping("/verifyPin")
	public ResponseEntity<Map<String,String>> verifyPin(@RequestBody PinVerification pinVerification){
		Map<String,String> map=new HashMap<>();
		if(handimanService.verifyPin(pinVerification)) {
			map.put("successMsg","Pin Verified");
			 return new ResponseEntity<>(map,HttpStatus.OK);
		}else {
			 map.put("errorMsg","Wrong pin");
			 return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		 }
	}
	@PostMapping("/forgotpassword")
	public ResponseEntity<Map<String,String>> verifyPin(@RequestBody String email){
		Map<String,String> map=new HashMap<>();
		if(handimanService.forgotpassword(email)) {
			map.put("successMsg","pin sent on user email");
			 return new ResponseEntity<>(map,HttpStatus.OK);
		}else {
			 map.put("errorMsg","email not present");
			 return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		 }
	}
	@PostMapping("/forgotPwdDetail")
	public ResponseEntity<Map<String,String>> changePwd(@RequestBody ForgotPwdDetails forgotPwdDetails){
		Map<String,String> map=new HashMap<>();
		if(handimanService.changePwd(forgotPwdDetails)) {
			map.put("successMsg","Password Chnaged");
			 return new ResponseEntity<>(map,HttpStatus.OK);
		}else {
			 map.put("errorMsg","wrong otp");
			 return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		 }
	}

}
