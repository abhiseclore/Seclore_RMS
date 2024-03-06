<%@page import="com.seclore.main.domain.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	HttpSession ses = request.getSession();
	UserDetails user = (UserDetails) ses.getAttribute("loggedInUser");
	%>
	<form:form action="updateuser" method="post">
		<table>
			<tr>
				<td>UserId</td>
				<td><input type="number" name="userId" path="userId"
					value="<%=user.getUserId()%>" readonly></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" path="name"
					value="<%=user.getName()%>"></td>
			</tr>
			<tr>
				<td>Position</td>
				<td><input type="text" name="position" path="position"
					value="<%=user.getPosition()%>"></td>
			</tr>
			<tr>
				<td><input type="submit" value="update"></td>
			</tr>
		</table>

	</form:form>
</body>
</html>