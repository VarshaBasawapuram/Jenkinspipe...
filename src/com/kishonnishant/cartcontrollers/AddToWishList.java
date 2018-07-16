package com.kishonnishant.cartcontrollers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kishonnishanth.models.SQLDatabaseInstance;

/**
 * Servlet implementation class AddToWishList
 */
public class AddToWishList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToWishList() {
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
		int productId = 0;
		SQLDatabaseInstance sqlDBInstance = SQLDatabaseInstance.getSQLDBI();
		System.out.println(request.getSession().getAttribute("email"));
		System.out.println("What");
		Enumeration<String> attr = request.getParameterNames();
		while (attr.hasMoreElements()) {
			productId = Integer.parseInt(request.getParameter(attr.nextElement()));
		}
		System.out.println(sqlDBInstance.addToWishList(request.getSession().getAttribute("email") + "", productId));
		RequestDispatcher rDispatcher = request.getRequestDispatcher("Views/wishList.jsp");
		rDispatcher.forward(request, response);
	}

}
