<%@page import="com.seclore.main.domain.BookingViewDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Bookings</title>
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
td{
	padding: 10px;
}
.operations{
	display: flex;
	gap: 10px;
}
</style>
</head>
<body>
	<h1>All Bookings</h1>
	<table>
		<thead>
			<th>Booking Id</th>
			<th>Room Id</th>
			<th>Start Time</th>
			<th>End Time</th>
			<th>Date</th>
			<th>Operations</th>
		</thead>
		<tbody>
			<c:forEach var="allBookings" items="${allBookingViewDetailsByUserId}">
				<tr>
					<td>${allBookings.getBookingSlots().getBooking().getBookingId()}</td>
					<td>${allBookings.getRoomDetails().getRoomId()}</td>
					<td>${allBookings.getSlotMaster().getStartTime()}</td>
					<td>${allBookings.getSlotMaster().getEndTime()}</td>
					<td>${allBookings.getSlotMaster().getDate()}</td>
					<td>${allBookings.getBookingSlots().getBooking().getDescription()}
					<td>
						<div class="operations">
							<form action="/bookingdetails/updateslot">
								<input type="hidden" value="${i}" name="index"> <input
									type="submit" value="Update Slot">
							</form>
							<form action="/bookingdetails/delete">
							<input type="hidden" value="${i}" name="index">
							<input type="submit" value="Delete">
							</form>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>