/*
 * 
 * This is the definition of the Employee object
 * 
 * */

public class Employee{
	
	private String pps, surname, firstName, department;
	private int employeeId;
	private char gender;
	private double salary;
	private boolean fullTime;

	// Create Employee with no details
	public Employee() {
		this.employeeId = 0;
		this.pps = "";
		this.surname = "";
		this.firstName = "";
		this.gender = '\0';
		this.department = "";
		this.salary = 0;
		this.fullTime = false;
	}//end Employee with no details
	
	
	
	
	
	//COULD DO A CREATION METHOD SPECIFIC TO EMPLYEE PERSONAL DETAILS, - NAME, SURNAME, PPSNUMBER, GENDER

	// Create Employee with details - CREATION METHOD
	public Employee(int employeeId, String pps, String surname, String firstName, char gender, String department, double salary,
			boolean fullTime) {
		this.employeeId = employeeId;
		this.pps = pps;
		this.surname = surname;
		this.firstName = firstName;
		this.gender = gender;
		this.department = department;
		this.salary = salary;
		this.fullTime = fullTime;
	}// end Employee with details
	
	
	//CAN CREATE SPECIFIC METHODS FOR CRETING PEOPLE/ EMPLOYEE DETAILS (MAY USE INTERFACE FOR THIS INSTEAD)

	// Getter methods
	public int getEmployeeId() {
		return this.employeeId;
	}

	public String getPps() {
		return pps;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public char getGender() {
		return this.gender;
	}

	public String getDepartment() {
		return this.department;
	}

	public double getSalary() {
		return this.salary;
	}

	public boolean getFullTime() {
		return this.fullTime;
	}

	// Setter methods
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public void setPps(String pps) {
		this.pps = pps;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}

	// Display Employee details
	public String toString() {
		String bool = "";
		if (fullTime)
			bool = "Yes";
		else
			bool = "No";

		return "Employee ID: " + this.employeeId + "\nPPS Number: " + this.pps + "\nSurname: " + this.surname
				+ "\nFirst Name: " + this.firstName + "\nGender: " + this.gender + "\nDepartment: " + this.department + "\nSalary: " + this.salary
				+ "\nFull Time: " + bool;
	}// end toString
}// end class Employee