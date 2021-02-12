package com.sysview.docauto.microservice.users.service;

import java.util.List;

import com.sysview.docauto.microservice.users.model.User;

public interface UserService {
	
	User save(User user); 
	
	User update(String username, User user);
	
	List<User> getAll();

	User findByUsername(String username);

	Integer delete(String username);

}
