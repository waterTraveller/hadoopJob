package com.topeastic.hadoop.dao;

import java.util.List;

import com.topeastic.hadoop.entity.User;


public interface UserDao {

	/**
	 * @Title findByUserNameAndPassword
	 * @todo 根据用户名和密码验证登录
	 * @param username
	 * @param password
	 * @return
	 */
	User findByUserNameAndPassword(String username, String password);
	
	List<User> getAll(User u);
	
	boolean deleteUserById(String id);

	boolean add(User user);
	
	User findByUsername(String username);
}
