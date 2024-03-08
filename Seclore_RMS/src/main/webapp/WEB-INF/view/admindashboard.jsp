<%@page import="com.seclore.main.domain.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin DashBoard</title>
</head>
<body>
	<table>
		<tr>
			<form action="updatepassword">
				<td>change password</td>
				<td><input type="submit" value="update"></td>
			</form>
		</tr>
		<tr>
			<form action="updateinfo">
				<td>update profile</td>
				<td><input type="submit" value="update"></td>
			</form>
		</tr>
		<tr>
			<form action="adduser">
				<td>Add new user</td>
				<td><input type="submit" value="add new user"></td>
			</form>
		</tr>
				<tr>
			<form action="getallusers" method="get">
				<td>>Users List</td><td><input type="submit" value="submit"></td>
			</form>
		</tr>
		<tr>
			<form action="/roomdetails/allrooms">
				<td>Rooms</td><td><input type="submit" value="submit"></td>
			</form>
		</tr>

		<tr>
			<form action="getbookingrequirements" method="get">
				<td>Add Booking</td><td><input type="submit" value="submit"></td>
			</form>
		</tr>
		<tr>
			<form action="bookingdetails/showallbookingsbyadmin">
				<td>List All booking</td><td><input type="submit" value="submit"></td>
			</form>
		</tr>

	</table>

</body>
</html>