package com.store.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection conn;

	private static String url = "jdbc:mysql://localhost:3306/BFStore?useUnicode=true&characterEncoding=utf8";
	private static String name = "root";
	private static String pwd = "mysql";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, name, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
