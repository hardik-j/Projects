<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Team Meetings</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
			<table class="table table-striped">
				<tr>
					<td><strong>Meeting ID</strong></td>
					<td><strong>Agenda</strong></td>
				</tr>
				<c:forEach items="${ meetingList }" var="meeting">
					<tr>
						<td>${ meeting.meetingId }</td>
						<td>${ meeting.agenda }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-lg-3"></div>
	</div>
</body>
</html>