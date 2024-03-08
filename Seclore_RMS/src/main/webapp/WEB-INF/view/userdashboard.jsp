<%@page import="com.seclore.main.domain.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User DashBoard</title>
<style type="text/css">
html, body {
    height: 100%;
}

html {
    margin: auto;
}

body {

}
table{
	font-size: large;
	column-gap: 10%;
}
form{
		padding: 2rem;
	}
	
	td{
		padding-bottom: 15px;
	}
</style>
</head>

<body>
	<%
	UserDetails user = (UserDetails) session.getAttribute("loggedInUser");
	if (user.getPosition() == "admin"||user.getPosition() == "Admin") {
		response.sendRedirect("admindashboard");
	}
	%>
	<table>
		<tr>
				<td><a href="updatepassword">change password</a> </td>
				
		</tr>
		<tr>
			<form action="updateprofile" method="get">
				<td>change user information</td>
				<td><input type="submit" value="update"></td>
			</form>
		</tr>
		<tr>
			<form action="bookingdetails/showallbookings">
				<td>show all bookings</td>
				<td><input type="submit" value="show"></td>
			</form>
		</tr>
		<tr>
			<form action="getbookingrequirements">
				<td>Add new booking</td>
				<td><input type="submit" value="Add"></td>
			</form>
		</tr>


	</table>
	<p><%=session.getAttribute("message")%></p>
</body>
</html>