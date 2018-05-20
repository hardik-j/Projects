<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="<c:url value="/resources/js/task.js" />"></script>
<title>My tasks</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<div class="col-lg-4">
			<div class="col-lg-12" align="left">
				<div class="row">
					<table class="table table-striped">
						<tr>
							<td><strong>ID</strong></td>
							<td><strong>Due on</strong></td>
							<td><strong>Description</strong></td>
							<td><strong>Status</strong></td>
						</tr>
						<c:forEach items="${ tasks }" var="task">
							<tr>
								<td><input type="button" class="btn btn-link.active"
									value="${ task.taskId }" onclick="loadTask(${ task.taskId })"></td>
								<td>${ task.expectedBy }</td>
								<td>${ task.description }</td>
								<td>${ task.status }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-2"></div>
		<div class="col-lg-4">
			<form action="" method="POST" class="form-horizontal">
				<div class="form-group">
					<input class="form-control input-lg" type="text"
						disabled="disabled" id="taskId" value="">
				</div>
				<div class="form-group">
					<input type="text" class="form-control input-lg"
						disabled="disabled" id="taskDescription" value="">
				</div>
				<div class="form-group">
					<select class="form-control" id="taskStatus" ></select>
				</div>
				<div class="form-group">
					<input type="text" class="form-control input-lg" value=""
						id="taskComment" />
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-primary btn-block"
						onclick="updateTask()" value="Update Status">
				</div>
			</form>
		</div>
		<div class="col-lg-2"></div>
	</div>
</body>
</html>