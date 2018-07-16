package com.kishonnishant.cartcontrollers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kishonnishanth.models.SQLDatabaseInstance;
//import com.kishonnishanth.models.SQLDatabaseInstance;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private SQLDatabaseInstance sqlInstance;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/Views/login.jsp");
		Enumeration<String> attr =  request.getParameterNames();
		while(attr.hasMoreElements()){
			System.out.println(request.getParameter(attr.nextElement()));
		}
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		
		User user = new User(userName, email, phoneNumber, password);
		if(SQLDatabaseInstance.getSQLDBI().addUserToDB(user)) {
			reqDispatcher.forward(request, response);
		}

	}

}
