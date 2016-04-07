package com.topeastic.hadoop.service;

import java.util.List;

import com.topeastic.hadoop.entity.User;


public interface UserService {
	
	/**
	 * 验证登陆
	 * @param username
	 * @param password
	 * @return
	 */
	User checkLogin(String username, String password);
	
	List<User> getAll(User u); 
	
	boolean deleteUserById(String id);
	
	boolean add(User user);
}
