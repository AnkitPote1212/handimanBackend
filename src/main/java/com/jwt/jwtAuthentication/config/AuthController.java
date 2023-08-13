package com.jwt.jwtAuthentication.config;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwtAuthentication.model.JwtRequest;
import com.jwt.jwtAuthentication.model.JwtResponse;
import com.jwt.jwtAuthentication.model.User;
import com.jwt.jwtAuthentication.model.UserPojo;
import com.jwt.jwtAuthentication.repo.CityRepository;
import com.jwt.jwtAuthentication.repo.HandimanUserRepository;
import com.jwt.jwtAuthentication.repo.UserPincodeRepository;
import com.jwt.jwtAuthentication.repo.UserRepository;
import com.jwt.jwtAuthentication.entity.HandimanUserEntity;
import com.jwt.jwtAuthentication.model.DummyUser;
import com.jwt.jwtAuthentication.model.HandimanInfo;
import com.jwt.jwtAuthentication.security.JwtHelper;
import com.jwt.jwtAuthentication.service.UserService;

@RestController
@RequestMapping
@CrossOrigin
public class AuthController {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    
	@Autowired
	private UserService userService; 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HandimanUserRepository handimanUserRepository;
	
	@Autowired
	private UserPincodeRepository userPincodeRepository;
	
	@Autowired
	private CityRepository cityRepository;
	

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        Object user=null;
        User appUser=userRepository.findByEmail(userDetails.getUsername());
        if(appUser!=null) {
        user=new UserPojo(appUser.getFirstName(),appUser.getLastName(),appUser.getEmail(),cityRepository.findById(appUser.getCityId()).get().getCityName(),appUser.getPassword(),appUser.getRole());
        }else{
        	HandimanUserEntity handiman=handimanUserRepository.findByEmail(userDetails.getUsername());
        	if(handiman!=null) {
        	List<Long> postalCodeList=userPincodeRepository.findByUserId(handiman);
        	user=new HandimanInfo(handiman.getFirstName(),handiman.getLastName(),handiman.getCity().getCityName(),handiman.getService().getServiceName(),0f,handiman.getContactNo(),handiman.getEmail(),handiman.getProfileImgUrl(),handiman.getAboutMe(),postalCodeList,handiman.getRole(),handiman.getResumeUrl());
        	}
        }
        response.setUser(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/hello")
	public List<DummyUser> showWelcome() {
		return userService.getStore();
	}
	
	@GetMapping("/currentUser")
	public String currentUser(Principal principal) {
		return principal.getName();
	}

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!",e);
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> exceptionHandler(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed: " + e.getMessage());
    }


}
