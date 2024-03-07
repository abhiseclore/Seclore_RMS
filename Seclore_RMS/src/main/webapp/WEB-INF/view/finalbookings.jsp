<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Finalized Bookings</title>
	</head>
	<body>
		<h2>
			Booking Successful! Here are your finalized booking(s):
		</h2>
		<hr>
		<c:forEach var="bookingViewDetails" items="${allBookingViewDetails}">
			<table>
				<tr>
					<td>
						Booking ID
					</td>
					<td>
						${bookingViewDetails.getBookingSlots().getBooking().getBookingId()}
					</td>
				</tr>
				<tr>
					<td>
						Room ID
					</td>
					<td>
						${bookingViewDetails.getRoomDetails().getRoomId()}
					</td>
				</tr>
				<tr>
					<td>
						Room Name
					</td>
					<td>
						${bookingViewDetails.getRoomDetails().getRoomName()}
					</td>
				</tr>
				<tr>
					<td>
						Date
					</td>
					<td>
							${bookingViewDetails.getSlotMaster().getDate()}
					</td>
				</tr>
				<tr>
					<td>
						Time
					</td>
					<td>
						${bookingViewDetails.getSlotMaster().getStartTime()} - ${bookingViewDetails.getSlotMaster().getEndTime()}
					</td>
				</tr>
			</table>
			<hr>
		</c:forEach>
	</body>
</html>