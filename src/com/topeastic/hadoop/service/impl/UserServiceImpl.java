package com.topeastic.hadoop.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topeastic.hadoop.dao.UserDao;
import com.topeastic.hadoop.entity.User;
import com.topeastic.hadoop.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Override
	public User checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.findByUserNameAndPassword(username, password);
	}

	@Override
	public List<User> getAll(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
