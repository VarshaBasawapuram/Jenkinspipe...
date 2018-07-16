package com.kishonnishant.cartcontrollers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kishonnishanth.models.SQLDatabaseInstance;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/Views/products.jsp");
		Enumeration<String> attr =  request.getParameterNames();
		while(attr.hasMoreElements()){
			System.out.println(request.getParameter(attr.nextElement()));
		}
	    SQLDatabaseInstance sq = SQLDatabaseInstance.getSQLDBI();
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
        if(sq.checkUserInDB(email, password)) {
        	HttpSession session = request.getSession(true);
        	session.setAttribute("email", email);
        	session.setAttribute("isLoggedIn",true);
        	reqDispatcher.forward(request, response);
        }else {
        	
        }
		System.out.println("In login controller");
	}

}
