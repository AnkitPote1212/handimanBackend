package com.jwt.jwtAuthentication.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.jwt.jwtAuthentication.repo.UserRepository;

@Configuration
public class AppConfig {
	
//	@Autowired
//	private UserRepository userRepository;
//
//	 @Bean
//	    public UserDetailsService userDetailsService() {
//		 
//		 List<UserDetails> userDetailsList = new ArrayList<>();
//	        
//	        // Fetch users from the database
//	        List<com.jwt.jwtAuthentication.model.User> users = userRepository.findAll();
//		 
//	        
//	        for (com.jwt.jwtAuthentication.model.User user : users) {
//	        UserDetails userDetails = User.builder().
//	                username(user.getEmail())
//	                .password(passwordEncoder().encode(user.getPassword())).roles(user.getRole()).
//	                build();
//	        userDetailsList.add(userDetails);
//	        }
//	        return new InMemoryUserDetailsManager(userDetailsList);
//	    }
//
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    	//return NoOpPasswordEncoder.getInstance();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	    @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587); // Set the appropriate port
	        mailSender.setUsername("handimanservices1999@gmail.com");
	        mailSender.setPassword("vhugfhuajdcngejh");

	        Properties properties = mailSender.getJavaMailProperties();
	        properties.put("mail.transport.protocol", "smtp");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.debug", "true");
	        return mailSender;
	    }
}
