package com.bl.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeePayrollConnection { // Singleton class

	private static EmployeePayrollConnection instance;

	Connection connection;
	String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
	String userName = "root";
	String password = "Saurabh@5675";

	private EmployeePayrollConnection() {

	}

	public static EmployeePayrollConnection getInstance() {
		if (instance == null) {
			instance = new EmployeePayrollConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println(Class.forName("com.mysql.cj.jdbc.Driver"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("Connecting to database" + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection is successful!!" + connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
