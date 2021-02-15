package com.sysview.docauto.microservice.users.dao;

import java.util.List;

import com.sysview.docauto.microservice.users.model.User;

public interface UserDAO {

	User save(User user); 
	
	User update(String username, User user);
	
	List<User> getAll();

	User findByUsername(String username);

	Integer delete(String username);

}
