<%@page import="com.seclore.main.domain.RoomDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Available Rooms</title>
<style type="text/css">
body {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 2rem;
}

table, th, td {
	border: 1px solid;
}

table {
	border-collapse: collapse;
}

td, th {
	padding: 20px;
}
</style>
</head>
<body>
	<h1>Available Rooms</h1>
	<form action="/bookingdetails/add" method="post">
		<table>
			<thead>
				<tr>
					<th>Room ID</th>
					<th>Room Name</th>
					<th>Capacity</th>
					<th>White Board</th>
					<th>Audio Video Conferencing</th>
					<th>Select</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="room" items="${allAvailableRoom}">
					<tr>
						<td>${room.roomId}</td>
						<td>${room.roomName}</td>
						<td>${room.capacity}</td>
						<td><c:if test="${room.hasWhiteboard == true}">  
							Yes
						</c:if> <c:if test="${room.hasWhiteboard == false}">  
							No
						</c:if></td>
						<td><c:if test="${room.hasAudioVideo == true}">  
							Yes
						</c:if> <c:if test="${room.hasAudioVideo == false}">  
							No
						</c:if></td>
						<td><input type="radio" value="${room.roomId }" name="roomId"
							required="required"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h6>Enter Description</h6>
		<input type="text" required="required" name="description"> <br>
		<br> <input type="submit" value="Book" name="addBooking">
	</form>
</body>
</html>