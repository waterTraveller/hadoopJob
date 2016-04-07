package com.topeastic.hadoop.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接池
 * @author root
 *
 */
public class JdbcUtils {
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {
		Connection connection = local.get();
		if (connection == null) {
			connection = DbConn.getConnection();
			local.set(connection);
		}
		connection.setAutoCommit(false);
		return connection;

	}

	public static void commit() {
		try {
			if (local.get() != null) {
				local.get().commit();
				local.get().close();
				local.set(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() {
		try {
			if (local.get() != null) {
				local.get().rollback();
				local.get().close();
				local.set(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
