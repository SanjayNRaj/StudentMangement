package com.studentmangement.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import com.studentmangement.modelbean.Student;

public class StudentData {
	
	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost/jdbc_test";
		// Database credentials
		static final String USER = "root";
		static final String PASS = "";
		
	
	private static final String INSERT_STUDENT_DATA = "INSERT INTO StudentTbl" + "  ( id, name,fatherName,gender,DOB,regNo,department,year,email,mobile) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String INSERT_STUDENT_DATA_BY_ID = "SELECT name,fatherName,gender,DOB,regNo,department,year,email,mobile FROM StudentTbl WHERE id =?";
	private static final String INSERT_ALL_STUDENT_DATA = "SELECT name,fatherName,gender,DOB,regNo,department,year,email,mobile from StudentTbl";
	private static final String DELETE_STUDENT_DATA = "DELETE FROM StudentTbl WHERE id = ?";
	private static final String UPDATE_STUDENT_DATA = "UPDATE StudentTbl SET name = ?,fatherName =?,gendar=?, DOB=?, regNo=?, department=?, year=?,email=?,mobile=? where id = ?;";
	
	public StudentData() {
		super();
		
	}
	
	// Creating connections 
	protected Connection getConnection() {
		Connection conn = null;
	//	Statement stmt = null;
		try
		{
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//STEP 6: Clean-up environment
			
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//the method is used to insert the Student data 
	public void insertStudenData (Student student)throws SQLException {
		System.out.println(INSERT_STUDENT_DATA);
		
		// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_DATA)) 
			{
				preparedStatement.setString(1, student.getName());
				preparedStatement.setString(2, student.getFatherName());
				preparedStatement.setString(3, student.getGender());
				preparedStatement.setString(4, student.getDOB());
				preparedStatement.setString(5, student.getRegNo());
				preparedStatement.setString(6, student.getDepartment());
				preparedStatement.setString(7, student.getYear());
				preparedStatement.setString(8, student.getEmail());
				preparedStatement.setString(9, student.getMobile());
				
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} 
			catch (SQLException e) 
			{
				printSQLException(e);
			}
			
			//rs.close();
			//stmt.close();
			
		
	}
	
	//the method is used to Select single  Student data 
	public Student selectStudentData(int id) 
	{
		System.out.println();
		Student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_DATA_BY_ID);) 
		{
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) 
			{
				String Name = rs.getString("Name");
				String FatherName = rs.getString("FatherName");
				String Gender = rs.getString("Gender");
				String DOB = rs.getString("DOB");
				String RegNO = rs.getString("regNO");
				String Department = rs.getString("department");
				String Year = rs.getString("Year");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				
				student = new Student(id, Name, FatherName, Gender, DOB, RegNO, Department, Year, email, mobile);
			}
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return student;
	}
	
	//the method is used to Select ALL Student data 
	public List<Student> selectAllStudentData() 
	{
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Student> student = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALL_STUDENT_DATA);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next())
			{
				int id = rs.getInt("id");
				String Name = rs.getString("Name");
				String FatherName = rs.getString("FatherName");
				String Gender = rs.getString("Gender");
				String DOB = rs.getString("DOB");
				String RegNO = rs.getString("regNO");
				String Department = rs.getString("department");
				String Year = rs.getString("Year");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
			
				student.add(new Student(id, Name, FatherName, Gender, DOB, RegNO, Department, Year, email, mobile));
			}
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return student;
		
	}
	
	//the method is used to Delete the Student data 
	public boolean deleteStudent(int id) throws SQLException 
	{
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_DATA);) 
		{
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
		
	}
	
	//the method is used to Update the Student data 
	public boolean updateStudent(Student student)throws SQLException 
	{
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_DATA);) 
		{
			System.out.println("updated USer:"+statement);
			statement.setString(1, student.getName());
			statement.setString(2, student.getFatherName());
			statement.setString(3, student.getGender());
			statement.setString(4, student.getDOB());
			statement.setString(5, student.getRegNo());
			statement.setString(6, student.getDepartment());
			statement.setString(7, student.getYear());
			statement.setString(8, student.getEmail());
			statement.setString(9, student.getMobile());
			statement.setInt(9, student.getId());
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
		
	}
	
	
	//the method is used to handle the exceptions

	private void printSQLException(SQLException ex) 
	{
		
		for (Throwable e : ex)
		{
			if (e instanceof SQLException) 
			{
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) 
				{
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
		
	}

	
	


   
}
