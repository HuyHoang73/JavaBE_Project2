package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private static String DBURL = "jdbc:mysql://localhost:3306/estatebasic";
	private static String USERNAME = "root";
	private static String PASS = "";
	
	public static final Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(DBURL, USERNAME, PASS);
			System.out.println("Connected successfully");
			return conn;
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected failed");
		}
		return null;
	}
}
