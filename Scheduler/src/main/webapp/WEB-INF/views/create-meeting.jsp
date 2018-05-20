<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/meeting.js" />"></script>
<title>Create Meeting</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="row">
		<div class="container"></div>
		<form action="#" method="post">
			<input type="hidden" id="team" value="${ requestScope.team }" />
			<div class="col-lg-1"></div>
			<div class="col-lg-2 form-group">
				<label for="agenda" class="text-uppercase">Agenda:</label> <input
					class="form-control input-lg" name="agenda" id="agenda" type="text"
					required />
			</div>
			<div class="col-lg-8">
				<table class="table table-bordered table-hover" id="task-table">
					<thead>
						<tr>
							<th class="text-center">#</th>
							<th class="text-center">Description</th>
							<th class="text-center">User</th>
							<th class="text-center">Due date</th>
							<th class="text-center">Comment</th>
						</tr>
					</thead>
					<tbody>
						<tr id='addr0'>
							<td>1</td>
							<td><input type='text' name='description0' id='description0'
								placeholder='Description' class='form-control input-lg' required /></td>
							<td><select type='text' name='user0' id='user0'
								placeholder='User' class='form-control input-lg' required /></td>
							<td><input type='date' name='date0' id='date0'
								class='form-control datepicker input-lg' required /></td>
							<td><input type='text' name='comment0' id='comment0'
								placeholder='Comments' class='form-control input-lg' /></td>
						</tr>
						<tr id='addr1'></tr>
					</tbody>
				</table>
				<div class="form-group">
					<div class="row">
						<a id="add_row" class="btn btn-default pull-left">Add Row</a><a
							id='delete_row' class="pull-right btn btn-default">Delete Row</a>
					</div>
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-block btn-primary"
						onclick="createMeeting()" value="Register Meeting" />
				</div>
			</div>
			<div class="col-lg-1"></div>
		</form>
	</div>
</body>
</html>