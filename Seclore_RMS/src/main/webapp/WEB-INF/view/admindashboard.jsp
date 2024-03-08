<%@page import="com.seclore.main.domain.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin DashBoard</title>
<style type="text/css">
html, body {
	height: 100%;
}

html {
	margin: auto;
}

body {
	
}

table {
	font-size: large;
	column-gap: 10%;
}

form {
	padding: 2rem;
}

td {
	padding-bottom: 15px;
}
</style>
</head>
<body>
	<h1>DashBoard</h1>
	<table>
		<tr>
			<td><a href="updatepassword">change password</a></td>
		</tr>
		<tr>
			<td><a href="updateprofile">update profile</a></td>
		</tr>
		<tr>
			<td><a href="adduser">Add new user</a></td>
		</tr>
		<tr>
			<td><a href="getallusers"> Manage Users </a></td>
		</tr>
		<tr>
			<td><a href="/roomdetails/allrooms"> Manage Rooms</a></td>
		</tr>
		<tr>
			<td><a href="getbookingrequirements"> Add Booking</a></td>
		</tr>
		<tr>
			<td><a href="bookingdetails/showallbookingsbyadmin"> List
					All bookings</a></td>
		</tr>
		<tr>
		<td><a href="logout"> LogOut</a></td>
		</tr>
	</table>
	
	<br>
<p><%=session.getAttribute("message")%>
	<%session.setAttribute("message", ""); %>
	</p>

</body>
</html>