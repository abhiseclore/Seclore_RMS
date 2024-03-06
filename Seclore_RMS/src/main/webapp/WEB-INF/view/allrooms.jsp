<%@page import="com.seclore.main.domain.RoomDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Room Details</title>
<style type="text/css">
	body{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		gap: 2rem;
	}
	table, th, td {
  		border: 1px solid;
	}
	table{
		border-collapse: collapse;
	}
	td, th{
		padding : 20px;
	}
</style>
</head>
<body>
	<h1>All Rooms</h1>
	<table>
		<thead>
			<tr>
				<th>Room ID</th>
				<th>Room Name</th>
				<th>Capacity</th>
				<th>White Board</th>
				<th>Audio Video Conferencing</th>
				<th>Availability</th>
				<th>Update Room</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="room" items="${allRooms}">
				<tr>
					<td>${room.roomId}</td>
					<td>${room.roomName}</td>
					<td>${room.capacity}</td>
					<td>
						<c:if test="${room.hasWhiteboard == true}">  
							Yes
						</c:if>
						<c:if test="${room.hasWhiteboard == false}">  
							No
						</c:if> 
					</td>
					<td>
						<c:if test="${room.hasAudioVideo == true}">  
							Yes
						</c:if>
						<c:if test="${room.hasAudioVideo == false}">  
							No
						</c:if> 
					</td>
					<td>
						<c:if test="${room.isAvailable == true}">  
							Yes
						</c:if>
						<c:if test="${room.isAvailable == false}">  
							No
						</c:if>  
					</td>
					<td>
						<form action="/roomdetails/updateroom" method="get">
							<input type="hidden" value="${room.roomId }" name="roomId">
							<input type="submit" value="Update Room">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="/roomdetails/addroom" method="get">
		<input type="submit" value="Add New Room">
	</form>
</body>
</html>