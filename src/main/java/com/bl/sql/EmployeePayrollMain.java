package com.bl.sql;

import java.util.Scanner;

public class EmployeePayrollMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		boolean stop = true;
		do {
			System.out.println(
					"\nEnter your choice: \n1.	Show the table. \n2.	Add to table. \n3.	Delete from table. \n4.	Update the table. \n5.	Exit. ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				employeePayrollDBService.showTable();
				break;
			case 2:
				employeePayrollDBService.insertTable();
				break;
			case 3:
				employeePayrollDBService.deleteTable();
				break;
			case 4:
				employeePayrollDBService.updateTable();
				break;
			case 5:
				stop = false;
				break;
			}
		} while (stop);
	}
}
