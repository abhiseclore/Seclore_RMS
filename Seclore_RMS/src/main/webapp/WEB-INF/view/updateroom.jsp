<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Room</title>
<style type="text/css">
	body{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	
	form{
		padding: 2rem;
		border: 1px solid black;
	}
	
	td{
		padding-bottom: 15px;
	}
	
	.submit{
		display: flex;
		justify-content: center;
		padding-top : 25px;
	}
	.title-div{
		width: 100%;
		display: grid;
		grid-template-columns: 1fr 1fr 1fr;
		grid-template-rows: 1fr;
		align-items: center;
		justify-items: center;
	}
</style>
</head>
<body>
	<div class="title-div">
		<a href="/roomdetails/allrooms"> Back to All Rooms</a>
		<h1>Update Room</h1>
	</div>
	<form:form action="/roomdetails/updateroom" method="post" modelAttribute="roomDetails">
		<table>
			<tr>
				<td>Room ID : </td>
				<td><form:input path="roomId" type="number" value="${roomDetails.roomId }" readonly="true"/></td>
			</tr>
			<tr>
				<td>Room Name : </td>
				<td><form:input path="roomName" type="text" value="${roomDetails.roomName }" required="required"/></td>
			</tr>
			<tr>
				<td>Capacity : </td>
				<td><form:input path="capacity" type="number" value="${roomDetails.capacity }" required="required"/></td>
			</tr>
			<tr>
				<td>Whiteboard : </td>
				<td><form:checkbox path="hasWhiteboard" name="whiteboard"/></td>
			</tr>
			<tr>
				<td>Audio Video Conferencing : </td>
				<td><form:checkbox path="hasAudioVideo" name="audiovideo"/></td>
			</tr>
			<tr>
				<td>Available : </td>
				<td><form:checkbox path="isAvailable" name="isavailable"/></td>
			</tr>
		</table>
		<div class="submit">
			<input type="submit" value="Update">
		</div>
	</form:form>
</body>
</html>