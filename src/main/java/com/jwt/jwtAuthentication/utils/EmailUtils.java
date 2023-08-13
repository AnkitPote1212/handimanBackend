package com.jwt.jwtAuthentication.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	

    @Value("${spring.mail.username}") 
    private String sender;

    public void sendSimpleEmail(String to, String subject, String text) {
    	try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("handiman@noreply.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    	}
    	 
        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
