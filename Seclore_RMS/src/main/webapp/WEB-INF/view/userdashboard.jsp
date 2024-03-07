<%@page import="com.seclore.main.domain.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User DashBoard</title>
</head>
<body>
<%UserDetails user = (UserDetails) session.getAttribute("loggedInUser");
if(user.getPosition()=="admin"){
	response.sendRedirect("admindashboard.jsp");	
}%>
	<table>
	<tr><form:form action = "updatepassword" ><td>change password</td><td><input type = "submit" value="update"></td></form:form></tr>
	<tr><form:form action = "updateprofile"><td>change user information</td><td><input type = "submit" value="update"></td></form:form></tr>
	<tr><form:form action = "bookingdetails/showallbookings"><td>show all bookings</td><td><input type = "submit" value="show"></td></form:form></tr>
	<tr><form:form action = "getbookingrequirement"><td>Add new booking</td><td><input type = "submit" value="Add"></td></form:form></tr>
	
	
	</table>
	
</body>
</html>