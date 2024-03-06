<%@page import="com.seclore.main.domain.RoomDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All User Details</title>
<style type="text/css">
body {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 2rem;
}

table, th, td {
	border: 1px solid;
}

table {
	border-collapse: collapse;
}

td, th {
	padding: 20px;
}
</style>
</head>
<body>
	<h1>All Users</h1>
	<table>
		<thead>
			<tr>
				<th>User ID</th>
				<th>User Name</th>
				<th>Position</th>
				<th>IsActive</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userlist}">
				<tr>
					<td>${user.getUserId()}</td>
					<td>${user.getName()}</td>
					<td>${user.getPosition()}</td>
					<td><c:if test="${user.isActive == true}">  
							Yes
						</c:if> <c:if test="${user.isActive == false}">  
							No
						</c:if></td>

					<td>

			</c:forEach>
		</tbody>
	</table>
	<form action="admindashboard" method="post">
		<input type="submit" value="go to dashboard">
	</form>
</body>
</html>