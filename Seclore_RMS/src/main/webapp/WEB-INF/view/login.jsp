<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
html, body {
    height: 100%;
}

html {
    margin: auto;
}

body {
    vertical-align: middle;
    font-size: larger;
}
</style>

</head>
<body>
<nav></nav>

<h3 align="center">Meeting room management System</h3>
	<form:form action="userlogin" modelAttribute="userDetails"  >
	<hr>
		<table>
			<tr>
				<td>Login_id</td>
				<td><form:input path="userId" type = "number" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="login"></td>
			</tr>
		</table>
	</form:form>

	<br>
	<p style="color: red;">${message}</p>

</body>
</html>