<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Search Results</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<table class="table table-striped">
			<tr>
				<td>ID</td>
				<td>Description</td>
				<td>Assigned To :</td>
				<td>Due on :</td>
				<td>Status</td>
			</tr>
			<c:forEach items="${ results }" var="task">
				<tr>
					<td><input value="${ task.taskId }"
						class="form-control input-lg" disabled="disabled" /></td>
					<td><input value="${ task.description }"
						class="form-control input-lg" disabled="disabled" /></td>
					<td><input value="${ task.user.firstName }"
						class="form-control input-lg" disabled="disabled" /></td>
					<td><input value="${ task.expectedBy }"
						class="form-control input-lg" disabled="disabled" /></td>
					<td><input value="${ task.status }" class="form-control input-lg"
						disabled="disabled" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>