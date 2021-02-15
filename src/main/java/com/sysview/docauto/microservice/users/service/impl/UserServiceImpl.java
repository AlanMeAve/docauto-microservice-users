package com.sysview.docauto.microservice.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysview.docauto.microservice.users.dao.UserDAO;
import com.sysview.docauto.microservice.users.model.User;
import com.sysview.docauto.microservice.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserDAO userDAO;
	
	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public User save(User user) {
		return userDAO.save(user);
	}

	@Override
	public User update(String username, User user) {
		return userDAO.update(username, user);
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public Integer delete(String username) {
		return userDAO.delete(username);
	}

}
