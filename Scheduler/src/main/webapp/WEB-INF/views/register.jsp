<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
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
</head>
<body>
<%@ include file="navbar.jsp" %>
<!-- 	<h1 align="center"> -->
<!-- 		<strong>Registration Page</strong> -->
<!-- 	</h1> -->
	<div class="col-lg-4"></div>
	<div class="col-lg-6">
		<form:form modelAttribute="user" action="/hardik/user/add"
			method="POST">
			<br>
			<br>
			<div class="form-row">
				<div class="form-group col-lg-8">
					<form:input path="firstName" type="text" class="form-control"
						id="firstName" placeholder="First Name" required="required" />
				</div>
				<div class="form-group col-lg-8">
					<form:input path="lastName" type="text" class="form-control"
						id="lastName" placeholder="Last Name" required="required" />
				</div>
				<div class="form-group col-lg-8">
					<form:input path="username" type="text" class="form-control"
						id="username" placeholder="Username" required="required" />
				</div>
				<div class="form-group col-lg-8">
					<form:input path="password" type="password" class="form-control"
						id="password" placeholder="Password" required="required" />
				</div>
				<div class="form-group col-lg-8">
					<form:input path="email" type="email" class="form-control"
						id="email" placeholder="Email" required="required" />
				</div>
				<div class="form-group col-lg-8" align="center">
					<label for="roles">Select roles :</label><br />
					<c:forEach items="${ roles }" var="role">
						<form:checkbox path="roles" id="roles" value="${ role }" />
						<label> &nbsp; ${ role }</label>
					</c:forEach>
				</div>
				<div class="form-group col-lg-8">
					<label for="roles">Select team :</label><br />
					<c:forEach items="${ teams }" var="team">
						<form:radiobutton path="team" id="team" value="${ team }"
							required="required" />
						<label>${ team }</label>
						<br />
					</c:forEach>
				</div>
				<div class="form-group col-lg-8">
					<label for="captchaCode" class="prompt">Retype the
						characters from the picture:</label>
					<%
						// Adding BotDetect Captcha to the page
							Captcha captcha = Captcha.load(request, "CaptchaObject");
							captcha.setUserInputID("captchaCode");
							String captchaHtml = captcha.getHtml();
							out.write(captchaHtml);
					%>
					<input id="captchaCode" type="text" name="captchaCode"
						required />
				</div>
				<div class="form-group col-lg-8" align="center">
					<button type="submit" class="btn btn-primary btn-lg">Register</button>
				</div>
			</div>
		</form:form>
	</div>
	<div class="col-lg-4"></div>
</body>
</html>