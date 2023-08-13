package com.jwt.jwtAuthentication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.jwt.jwtAuthentication.repo.UserRepository;

@Service
public class UserManagemetService {

	@Autowired
	private UserRepository userRepository;
	
	
	public UserDetailsService userDetailsService() {
		 
		 List<UserDetails> userDetailsList = new ArrayList<>();
	        
	        // Fetch users from the database
	        List<com.jwt.jwtAuthentication.model.User> users = userRepository.findAll();
		 
	        
	        for (com.jwt.jwtAuthentication.model.User user : users) {
	        UserDetails userDetails = User.builder().
	                username(user.getEmail())
	                .password(passwordEncoder().encode(user.getPassword())).roles(user.getRole()).
	                build();
	        userDetailsList.add(userDetails);
	        }
	        return new InMemoryUserDetailsManager(userDetailsList);
	    }

	 
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	
}
