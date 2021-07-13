package com.studentmangement.Controlrs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studentmangement.Dao.StudentData;
import com.studentmangement.modelbean.Student;


@WebServlet("/")
public class StudentControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentData studentData;
       
	// Default constructor stub
    public StudentControllServlet() {
        super();
        
    }

    //  init method 
	public void init(ServletConfig config) throws ServletException {
		studentData = new StudentData();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getServletPath();

		try {
			switch (action) 
			{
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertStudent(request, response);
				break;
			case "/delete":
				deleteStudent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStudent(request, response);
				break;
			default:
				listStudent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException 
	{
		List<Student> listStudent = studentData.selectAllStudentData();
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentHome.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentDetails.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent = studentData.selectStudentData(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentDetails.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);

	}
	
	private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException 
	{
		String Name = request.getParameter("Name");
		String FatherName = request.getParameter("FatherName");
		String Gender = request.getParameter("Gender");
		String DOB = request.getParameter("DOB");
		String RegNo = request.getParameter("RegNo");
		String Department = request.getParameter("getDepartment");
		String Year = request.getParameter("Year");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		
		Student newStudent = new Student(Name, FatherName,Gender, DOB, RegNo, Department, Year, email, mobile);
		
		studentData.insertStudenData(newStudent);
		response.sendRedirect("list");
	}
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException 
	{
		//int id = Integer.parseInt(request.getParameter("id"));
		String Name = request.getParameter("Name");
		String FatherName = request.getParameter("FatherName");
		String Gender = request.getParameter("Gender");
		String DOB = request.getParameter("DOB");
		String RegNo = request.getParameter("RegNo");
		String Department = request.getParameter("getDepartment");
		String Year = request.getParameter("Year");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		
		Student upDateStudent = new Student(Name, FatherName,Gender, DOB, RegNo, Department, Year, email, mobile);
		studentData.updateStudent(upDateStudent);
		response.sendRedirect("list");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		studentData.deleteStudent(id);
		response.sendRedirect("list");

	}

	

	
}
