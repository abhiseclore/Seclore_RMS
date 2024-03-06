<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New User</title>


</head>
<body>
    <form:form action="addnewuser" method="post">
		<table>

			<tr>
				<td>Name</td>
				<td><input type="text" name="name" path="name" required></td>
			</tr>
			<tr>
				<td>Position</td>
				<td><input type="text" name="position" path="position" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="add"></td>
			</tr>
		</table>

	</form:form>
</body>
</html>