<%@page import="java.util.ArrayList"%>
<%@page import="com.kishonnishanth.models.SQLDatabaseInstance"%>
<%@page import="java.util.Map"%>
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
	SQLDatabaseInstance sqlDBInstance = SQLDatabaseInstance.getSQLDBI();
		Map<Integer, Integer> productDetails = sqlDBInstance.getCartListByUser(request.getSession().getAttribute("email") + "");
	%>
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
		if (request.getSession().getAttribute("isLoggedIn") != null) {
	%>
	<div class="container">
	<div class="row">
		<%
		for (Map.Entry<Integer, Integer> entry : productDetails.entrySet()) {
			Product product = sqlDBInstance.getProductById(entry.getKey());
		%>
		<div class="col-sm-4">
		<div class="card" style="width: 18rem;">
			<img class="card-img-top" src=<%=product.getProdURL()%>
				alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title">
					<%=
						product.getProdName()
					%>
				</h5>
				<h6 class="card-subtitle mb-2 text-muted">
					No of Items :
					<%=entry.getValue()%>
				</h6>
				<p class="card-text">
					<%=
						product.getProdDescription()
					%>
				</p>
				<form name="cart" method="post" action="/Cart2.0/CheckOut">
					<input type="hidden" name="hdnbt" />
					<button class="btn btn-primary">Check out</button>
					<br />
				</form>
			</div>
		</div>
		</div>
		<%
			}
		%>
		</div>
	</div>
	<%
		}
	%>
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