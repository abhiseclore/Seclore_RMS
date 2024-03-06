<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form:form action="userlogin" modelAttribute="user">
		<table>
			<tr>
				<td>Login_id</td>
				<td><form:input path="user_id" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="login"></td>
			</tr>
		</table>
	</form:form>

	<br>
	<p style="color: red;">${message}</p>

</body>
</html>