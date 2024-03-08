<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Booking Requirements</title>
		<script type="text/javascript" src="scripts/requirementvalidation.js" defer></script>
	</head>
	<body>
		<h2>
			Please enter your requirements here.
		</h2>
		<hr>
		<c:if test="${message != null}">
			<p>
				${message}
			</p>
			<hr>
		</c:if>
		<form action="add" method="post">
			<table>
				<tr>
					<td>
						Start Date
					</td>
					<td>
						<input type="date" name="startDate" id="startDate">
					</td>
				</tr>
				<tr>
					<td>
						End Date
					</td>
					<td>
						<input type="date" name="endDate" id="endDate">
					</td>
				</tr>
				<tr>
					<td>
						Start Time
					</td>
					<td>
						<input type="time" name="startTime" id="startTime" step="1800">
					</td>
				</tr>
				<tr>
					<td>
						End Time
					</td>
					<td>
						<input type="time" name="endTime" id="endTime" step="1800">
					</td>
				</tr>
				<tr>
					<td>
						How many people are you booking for?
					</td>
					<td>
						<input type="number" name="seatingCapacity">
					</td>
				</tr>
				<tr>
					<td>
						Do you need whiteboarding?
					</td>
					<td>
						<input type="checkbox" name="hasWhiteboard">
					</td>
				</tr>
				<tr>
					<td>
						Do you need audio-video support?
					</td>
					<td>
						<input type="checkbox" name="hasAudioVideo">
					</td>
				</tr>				
			</table>
			<input type="submit" value="Get Available Rooms">
		</form>
	</body>
</html>