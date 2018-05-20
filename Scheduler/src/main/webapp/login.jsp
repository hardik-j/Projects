<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" charset="text/html; utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function redirect() {
		var url = "/hardik/login/register";
		window.location.href = url;
	}
</script>
<title>Login</title>
</head>
<body class="bg-success">
	<br />
	<br />
	<br />
	<h1 align="center">
		<strong>Login or Sign Up</strong>
	</h1>
	<div class="container">
		<div class="row">
			<form
				action="${ pageContext.request.contextPath }/login/authenticate"
				method="POST">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<br> <br> <br> <br> <br>
					<div class="form-group">
						<label class="text-uppercase" for="email">Email:</label> <input
							type="text" name="username" class="form-control input-lg"
							placeholder="Username" required />
					</div>
					<div class="form-group">
						<label for="pwd" class="text-uppercase">Password:</label> <input
							type="password" name="password" class="form-control input-lg"
							placeholder="Password" required />
					</div>
					<div class="form-group" align="center">
						<button type="submit" class="btn btn-primary btn-lg btn-block">Log
							In</button>
					</div>
				</div>
				<div class="col-lg-4"></div>
			</form>
		</div>
	</div>
</body>
</html>