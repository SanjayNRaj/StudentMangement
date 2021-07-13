package com.studentmangement.modelbean;

public class Student 
{
	private int id;
	private String Name;
	private String FatherName;
	private String Gender;
	private String DOB;
	private String RegNo;
	private String Department;
	private String Year;
	private String email;
	private String mobile;
	
	public Student(String name, String fatherName, String gender, String dOB, String regNo, String department,
			String year, String email, String mobile) 
	{
		super();
		Name = name;
		FatherName = fatherName;
		Gender = gender;
		DOB = dOB;
		RegNo = regNo;
		Department = department;
		Year = year;
		this.email = email;
		this.mobile = mobile;
	}

	public Student(int id, String name, String fatherName, String gender, String dOB, String regNo, String department,
			String year, String email, String mobile) 
	{
		super();
		this.id = id;
		Name = name;
		FatherName = fatherName;
		Gender = gender;
		DOB = dOB;
		RegNo = regNo;
		Department = department;
		Year = year;
		this.email = email;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFatherName() {
		return FatherName;
	}

	public void setFatherName(String fatherName) {
		FatherName = fatherName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getRegNo() {
		return RegNo;
	}

	public void setRegNo(String regNo) {
		RegNo = regNo;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
	
	
	
	
	
	
}
