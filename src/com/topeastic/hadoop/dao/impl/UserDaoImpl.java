package com.topeastic.hadoop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topeastic.hadoop.dao.UserDao;
import com.topeastic.hadoop.entity.User;
import com.topeastic.hadoop.utils.JdbcUtils;

@Service("userDao")
public class UserDaoImpl implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Override
	public User findByUserNameAndPassword(String username, String password) {
		String sql = "select * from hadoop_user where username=? and userpasswd=? ";
		User user = null;
		try {
			PreparedStatement ps = JdbcUtils.getConnection().prepareStatement(
					sql);
			ps.setString(1, username);
			ps.setString(2, password);
			logger.info("查询登录用户信息的sql语句:" + sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserPasswd(rs.getString("userpasswd"));
				user.setUserRoler(rs.getString("userroler"));
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> getAll(User user) {
		String sql = getSql(user);
		List<User> userList = new ArrayList<User>();
		try {
			PreparedStatement ps = JdbcUtils.getConnection().prepareStatement(
					sql);
			logger.info("查询所有用户的sql:" + sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getString("id"));
				u.setUserName(rs.getString("username"));
				userList.add(u);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			logger.info("查询所有用户异常", e);
		}
		return userList;
	}

	/**
	 * 条件查询用户的sql
	 */
	private String getSql(User u) {
//		String sql = "select * from (select u.*, r.rolename, r.identity from user u left join user_role ur on u.id = ur.user_id left join role r on r.id = ur.role_id) a where 1=1 ";
//		if (u.getUserName() != null && !"".equals(u.getUserName())) {
//			sql += " and a.username = '" + u.getUserName() + "'";
//		}
//		sql += " order by id asc ";
//		return sql;
		return "";
	}

	@Override
	public boolean deleteUserById(String id) {
		String sql = "delete from hadoop_user where id = ? ";
		try {
			PreparedStatement ps = JdbcUtils.getConnection().prepareStatement(
					sql);
			ps.setString(1, id);
			ps.executeUpdate();
			logger.info("删除用户[id:" + id + "]：" + sql);
			ps.close();
			return true;
		} catch (Exception e) {
			logger.info("删除用户[id:" + id + "]异常", e);
			return false;
		}
	}

	@Override
	public boolean add(User user) {
		String sql = "insert into hadoop_user values(?,?,?,?) ";
		logger.info("增加用户的sql：" + sql);
		try {
			PreparedStatement ps = JdbcUtils.getConnection().prepareStatement(
					sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPasswd());
			ps.setString(4, user.getUserRoler());
			ps.executeUpdate();
			JdbcUtils.commit();
			return true;

		} catch (Exception e) {
			logger.info("添加失败" + e);
			return false;
		}
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
