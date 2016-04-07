package com.topeastic.hadoop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DbConn {

	private static final Logger logger = Logger.getLogger(DbConn.class);

	/**
	 * mysql数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			String driverName = Config.driverName;
			String url = Config.url;
			Class.forName(driverName);
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			logger.error("mysql database root conn exception :", e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 * @param rs
	 * @param state
	 */
	public static void close(Connection conn, ResultSet rs, Statement state) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("database conn close exception :", e);
			}
		}
		try {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {
				rs = null;
			}

			try {
				if (state != null) {
					state.close();
					state = null;
				}
			} catch (Exception e) {
				state = null;
			}

			try {
				if (conn != null) {
					if (!conn.getAutoCommit()) {
						conn.commit();
					}
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e2) {
					}
					try {
						conn.close();
					} catch (Exception e1) {
					}
					conn = null;
				}
			}
		} finally {
			rs = null;
			state = null;
			conn = null;
		}
	}
	public static void main(String[] args) {
		System.out.println(getConnection().toString());
	}

}
