<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%if(request.getSession().getAttribute("isLoggedIn")!=null){ %>
	<%@include file="header.html"%>
	<%} %>
	<%if((request.getSession().getAttribute("isLoggedIn") == null)){ %>
	<%@include file="headerWNLI.html"%>
	<%} %>
	<div class="container">
		<div class="col-sm-2"></div>
		<div class="col-sm-6">
			<h2>Login</h2>
			<form class="form-horizontal" action="/Cart2.0/LoginServlet"
				method="post">
				<div class="form-group">
					<label class="control-label col-sm-2">Email:</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="email"
							placeholder="Enter email" name="email">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">Password:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pwd"
							placeholder="Enter password" name="pwd">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">Login</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="col-sm-4"></div>
	<%@include file="footer.html"%>
</body>
</html>