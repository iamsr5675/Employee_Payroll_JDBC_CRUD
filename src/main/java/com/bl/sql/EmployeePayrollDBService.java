package com.bl.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeePayrollDBService implements PayrollInterface {

	String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
	String userName = "root";
	String password = "Saurabh@5675";
	Connection connection; // establishing the connection

	@Override
	public void showTable() {
		String sql = "SELECT * FROM employee_payroll;";
		try {
			Connection connection = EmployeePayrollConnection.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				System.out.println(result.getInt("id") + "  " + result.getString("name") + "  "
						+ result.getDouble("salary") + "  " + result.getDate("start"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insertTable() {
		try {
			Scanner sc = new Scanner(System.in);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into employee_payroll values(?,?,?,?)");
			System.out.println("Enter ID : ");
			int id = sc.nextInt();

			System.out.println("Enter Name : ");
			String name = sc.next();

			System.out.println("Enter salary : ");
			double salary = sc.nextDouble();

			System.out.println("Enter Start Date in the formate of YYYY-MM-DD : ");
			String date = sc.next();
			Date start = Date.valueOf(date);

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setDouble(3, salary);
			preparedStatement.setDate(4, start);

			int update = preparedStatement.executeUpdate();
			if (update != 0) {
				System.out.println(update);
				showTable();
				System.out.println("Row get inserted");
			} else {
				System.out.println("Insertion failed");
			}

		} catch (Exception e) {
			System.out.println("Query not executed");
		}

	}

	@Override
	public void deleteTable() {
		Scanner sc = new Scanner(System.in);
		String deleteSQL = "DELETE FROM employee_payroll WHERE id = ?";
		try (Connection connection = EmployeePayrollConnection.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
			System.out.println("Enter ID : ");
			int id = sc.nextInt();

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("Record Deleted successfully from database");

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void updateTable() {
		Scanner sc = new Scanner(System.in);
		String sqlUpdate;
		showTable();
		System.out.println("\nEnter your choice: \n1.	Update name. \n2.	Update salary. \n3.	Update date.");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			sqlUpdate = "UPDATE employee_payroll SET name = ?  WHERE id = ?";
			try (Connection connection = EmployeePayrollConnection.getInstance().getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {

				System.out.println("Enter the name: ");
				String name = sc.next();
				System.out.println("Enter the id: ");
				int id = sc.nextInt();

				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();

				System.out.println("Record Updated successfully from database");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			sqlUpdate = "UPDATE employee_payroll SET salary = ?  WHERE id = ?";
			try (Connection connection = EmployeePayrollConnection.getInstance().getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {

				System.out.println("Enter the salary: ");
				Double salary = sc.nextDouble();
				System.out.println("Enter the id: ");
				int id = sc.nextInt();

				preparedStatement.setDouble(1, salary);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();

				System.out.println("Record Updated successfully from database");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			sqlUpdate = "UPDATE employee_payroll SET start = ?  WHERE id = ?";
			try (Connection connection = EmployeePayrollConnection.getInstance().getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {

				System.out.println("Enter the date: ");
				String start = sc.next();
				System.out.println("Enter the id: ");
				int id = sc.nextInt();

				preparedStatement.setString(1, start);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();

				System.out.println("Record Updated successfully from database");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		}

	}
}
