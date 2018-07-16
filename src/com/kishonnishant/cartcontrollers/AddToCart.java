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
 * Servlet implementation class AddToCart
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
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
		// this.doPost(request, response);
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Enumeration<String> attr = request.getParameterNames();
		SQLDatabaseInstance sqlDBInstance = SQLDatabaseInstance.getSQLDBI();
	    int productId = 0;
	    if(request.getParameter("wishProductId")!=null) {
			sqlDBInstance.deletePoroductInWishList(""+request.getSession().getAttribute("email"), Integer.parseInt(request.getParameter("wishProductId")));
	    }
	    System.out.println(request.getSession().getAttribute("email"));
		while (attr.hasMoreElements()) {
			productId = Integer.parseInt(request.getParameter(attr.nextElement()));
			System.out.println("Hello" + productId+"         ");
		}
		sqlDBInstance.addToCart(request.getSession().getAttribute("email")+"", productId);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("Views/cart.jsp");
		rDispatcher.forward(request, response);
	}

}
