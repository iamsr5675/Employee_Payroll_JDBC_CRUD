package com.bl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import com.mysql.cj.jdbc.Driver;

public class Jdbc {
	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "Saurabh@5675";
		Connection connection; // establishing the connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // check if the driver is loaded
			System.out.println("Driver Loaded");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}

		listDrivers();
		try {
			System.out.println("Connecting to database : " + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, userName, password); // DriverManager is the interface,
																					// it's the API that it is using to
																					// establishing the connection.
			System.out.println("Connection is successful!!!!" + connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void listDrivers() { // shows the list of drivers loaded in the driver manager
		Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println("  " + driverClass.getClass().getName());
		}

	}

}
