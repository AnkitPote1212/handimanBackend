package com.jwt.jwtAuthentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.jwtAuthentication.entity.HandimanUserEntity;
import com.jwt.jwtAuthentication.model.CustomUserDetails;
import com.jwt.jwtAuthentication.model.User;
import com.jwt.jwtAuthentication.repo.HandimanUserRepository;
import com.jwt.jwtAuthentication.repo.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private HandimanUserRepository handimanUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=this.userRepository.findByEmail(username);
		if(user==null) {
			HandimanUserEntity handimanUser=handimanUserRepository.findByEmail(username);
			user=new User(handimanUser.getUserId(),handimanUser.getFirstName(),handimanUser.getLastName(),handimanUser.getPassword(),handimanUser.getEmail(),"HANDIMAN_USER",handimanUser.getCity().getCityId(),true);
		}
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			return new CustomUserDetails(user);
		}
	}

}
