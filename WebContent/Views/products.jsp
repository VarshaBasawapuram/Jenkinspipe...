<%@page import="java.util.ArrayList"%>
<%@page import="com.kishonnishanth.models.SQLDatabaseInstance"%>
<%@page import="java.util.List"%>
<%@page import="com.kishonnishant.cartcontrollers.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		if (request.getSession().getAttribute("isLoggedIn") != null) {
	%>
	<%@include file="header.html"%>
	<%
		}
	%>
	<%
		if ((request.getSession().getAttribute("isLoggedIn") == null)) {
	%>
	<%@include file="headerWNLI.html"%>
	<%
		}
	%>
	<%		
		List<Product> products = SQLDatabaseInstance.getSQLDBI().getAllProductsDetails();
	%>
	<div class="container">
	<div class="row">
	
		<%
			for (Product product : products) {
		%>
		<div class="col-sm-4">
		<div class="card" style="width: 18rem;">
			<img class="card-img-top" src=<%=product.getProdDescription()%>
				alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title"><%=product.getProdName()%></h5>
				<p class="card-text"><%=""+product.getProdURL()%></p>
				<%
					if (request.getSession().getAttribute("isLoggedIn") != null) {
				%>
				<form name="cart" method="post" action="/Cart2.0/AddToCart">
					<input type="hidden" name="productID"
						value=<%=product.getProdId()%>> <input type="submit"
						class="btn btn-primary" value="Add to cart" />
				</form>
				<%
					}
				%>
				<%
					if (request.getSession().getAttribute("isLoggedIn") == null) {
				%>
				<form name="cart" method="post" action="/Cart2.0/Views/login.jsp">
					<input type="submit" class="btn btn-primary" value="Add to cart" />
				</form>
				<%
					}
				%>

				<br />
				<form name="cart" method="post" action="/Cart2.0/AddToWishList">
					<input type="hidden" name="productID"
						value=<%=product.getProdId()%>> <input type="submit"
						class="btn btn-primary" value="Add to WList" />
				</form>
			</div>
		</div>
		</div>
		<%
			}
		%>
		</div>

	</div>
	<%@include file="footer.html"%>
</body>
</html>