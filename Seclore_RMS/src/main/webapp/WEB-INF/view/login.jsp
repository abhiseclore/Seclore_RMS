<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style type="text/css">
html, body {
    height: 100%;
}

html {
    margin: auto;
}

body {
	min-height: 100vh;
	width: 100%;
    vertical-align: middle;
    font-size: larger;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
}
tr{
	padding-bottom: 25px;
}
nav{
	width: 100%;
}	
</style>

</head>
<body>
<nav>
	<h1 align="center">Meeting room management System</h1>
	<hr>
</nav>
	<form:form action="userlogin" modelAttribute="userDetails"  >
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
				<td colspan="2" align="center"><input type="submit" value="login"></td>
			</tr>
		</table>
	</form:form>

	<br>
	<p style="color: red;">${message}</p>

</body>
</html>