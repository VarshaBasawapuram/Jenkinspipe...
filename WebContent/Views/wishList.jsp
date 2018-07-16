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
		List<Integer> productIds = SQLDatabaseInstance.getSQLDBI()
				.getWishListByUser(request.getSession().getAttribute("email") + "");
		List<Product> products = new ArrayList<>();
		SQLDatabaseInstance sqlDBInstance = SQLDatabaseInstance.getSQLDBI();
		for (int productId : productIds) {
			products.add(sqlDBInstance.getProductById(productId));
		}
	%>
	<%if(request.getSession().getAttribute("isLoggedIn")!= null){ %>
	<%@include file="header.html"%>
	<%} %>
	<%if((request.getSession().getAttribute("isLoggedIn") == null)){ %>
	<%@include file="headerWNLI.html"%>
	<%} %>
		<%if(request.getSession().getAttribute("isLoggedIn")!= null){ %>
	<div class="container">
	<div class="row">
	<% for(Product product : products){%>
	<div class="col-sm-4">
		<div class="card" style="width: 18rem;">
			<img class="card-img-top"
				src= <%=product.getProdURL()%>
				alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title"><%=product.getProdName()%></h5>
				<p class="card-text"><%=product.getProdDescription()%></p>
				<form name="cart" method="post" action="/Cart2.0/AddToCart">
					<input type="hidden" name="wishProductId" value=<%=product.getProdId()%> />
					<button class="btn btn-primary">Add to cart</button>
					<br />
				</form>
			</div>
		</div>
		<%} %>
	</div>
	</div>
	<%} %>
	</div>
	<%
			if (request.getSession().getAttribute("isLoggedIn") == null) {
		%>
	<h1>No Items present in the cart</h1>
	<%
			}
		%>
	<%@include file="footer.html"%>
</body>
</html>