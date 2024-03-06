<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>

	<h1>update password</h1>

	<form:form action = "passupdate" method = "post">
		<table>
			<tr>
				<td>old password</td>
				<td><input type="password" name="oldpass"></td>
			</tr>

			<tr>
				<td>new password</td>
				<td><input type="password" name="newpass"></td>
			</tr>
			<tr>
				<td>new password again</td>
				<td><input type="password" name="renewpass"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="update"></td>
			</tr>
		</table>

	</form:form>

</body>
</html>