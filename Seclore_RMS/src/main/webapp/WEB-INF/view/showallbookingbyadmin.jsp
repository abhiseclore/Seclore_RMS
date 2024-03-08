<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
	<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Bookings</title>
</head>
<body>
	<h1>All Bookings</h1>
	<table>
		<thead>
			<th>Booking Id</th>
			<th>Room Id</th>
			<th>User Id</th>
			<th>Start Time</th>
			<th>End Time</th>
			<th>Date</th>
			<th>Operations</th>
		</thead>
		<tbody>
		<c:set var="i" value="0"/>
			<c:forEach var="allBookings" items="${allBookingViewDetailsByAdmin}">
				<tr>
					<td>${allBookings.getBookingSlots().getBooking().getBookingId()}</td>
					<td>${allBookings.getRoomDetails().getRoomId()}</td>
					<td>${allBookings.getBookingSlots().getBooking().getUser().getUserId()}
					<td>${allBookings.getSlotMaster().getStartTime()}</td>
					<td>${allBookings.getSlotMaster().getEndTime()}</td>
					<td>${allBookings.getSlotMaster().getDate()}</td>
					<td>${allBookings.getBookingSlots().getBooking().getDescription()}
					<td><form action="/bookingdetails/updateslot">
							<input type="hidden" value="${i}" name="index"> <input
								type="submit" value="Update Slot">
						</form></td>
					<td><form action="/bookingdetails/delete">
						<input type="hidden" value="${i}" name="index">
						<input type="submit" value="Delete">
						</form></td>
				</tr>
				<c:set var="i" value="${i+1}"/>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>