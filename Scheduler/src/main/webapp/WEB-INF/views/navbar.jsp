<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("user") == null)
			response.sendRedirect("http://localhost:8080/hardik/login");
	%>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><c:out
					value="${ user.firstName }  ${ user.lastName }"></c:out> </a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${ pageContext.request.contextPath }/task/mytask">My
					tasks</a></li>
			<c:forEach items="${ user.roles }" var="role">
				<c:if test="${ role eq 'ADMIN'}" var="admin">
					<li><a
						href="${ pageContext.request.contextPath }/login/register">Create
							a user</a></li>
				</c:if>
				<c:if test="${ role eq 'MANAGER' or role eq 'LEAD'}" var="manager">
					<c:if test="${ role eq 'MANAGER'}" var="manager">
						<li><a
							href="${ pageContext.request.contextPath }/meeting/add">Create
								a meeting</a></li>
						<li><a
							href="${ pageContext.request.contextPath }/meeting/get">Team
								Meetings</a></li>
					</c:if>
				</c:if>
			</c:forEach>
			<form class="navbar-form navbar-left"
				action="http://localhost:8080/hardik/task/search">
				<div class="form-group">
					<input type="text" class="form-control" name="query"
						placeholder="Enter keyword">
				</div>
				<div class="form-group">
					<select class="form-control" id="type" name="type">
						<option value="user">User</option>
						<option value="status">Status</option>
						<option value="id">Id</option>
					</select>
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${ pageContext.request.contextPath }/login/logout"><span
					class="glyphicon glyphicon-log-in"></span>Logout</a></li>
		</ul>
	</div>
	</nav>
</body>
</html>