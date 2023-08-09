package com.jwt.jwtAuthentication;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jwt.jwtAuthentication.utils.EmailUtils;

@SpringBootApplication
public class JwtAuthenticationApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}

}
