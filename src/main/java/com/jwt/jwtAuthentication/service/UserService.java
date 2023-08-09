package com.jwt.jwtAuthentication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jwt.jwtAuthentication.model.DummyUser;

@Service
public class UserService {

	private List<DummyUser> store=new ArrayList<>();
	
	public UserService() {
		store.add(new DummyUser(UUID.randomUUID().toString(),"Durgesh Tiwari","durgesg@dev.in"));
		store.add(new DummyUser(UUID.randomUUID().toString(),"Poorva Tiwari","Poorva@dev.in"));
		store.add(new DummyUser(UUID.randomUUID().toString(),"Sourav Tiwari","Sourav@dev.in"));
		store.add(new DummyUser(UUID.randomUUID().toString(),"panthela Tiwari","panthela@dev.in"));
	}

	public List<DummyUser> getStore() {
		return store;
	}
}
