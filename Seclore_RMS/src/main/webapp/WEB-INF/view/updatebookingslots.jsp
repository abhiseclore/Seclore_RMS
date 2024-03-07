<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Update Timings</title>
	</head>
	<body>
		<h2>
			Change your booking timings here.
		</h2>
		<hr>
		<form action="">
			<table>
				<tr>
					<td>
						Booking ID
					</td>
					<td>
						${bookingDetails.getBookingId()}
					</td>
				</tr>
				<tr>
					<td>
						Room ID
					</td>
					<td>
						${bookingDetails.getRoom.getRoomId()}
					</td>
				</tr>
				<tr>
					<td>
						Room Name
					</td>
					<td>
						${bookingDetails.getRoom.getRoomName()}
					</td>
				</tr>
				<tr>
					<td>
						Current timing
					</td>
					<td>
						${startTime} - ${endTime}
					</td>
				</tr>
				<tr>
					<td>
						Time 1
					</td>
					<td>
						<input type="time" name="newStartTime">
					</td>
				</tr>
				<tr>
					<td>
						Time 2
					</td>
					<td>
						<input type="time" name="newEndTime">
					</td>
				</tr>
				<tr>
					<td>
						Select an action
					</td>
					<td>
						<select required name="action">
							<option disabled selected hidden>Select...</option>
							<option value="SHRINK">Retain slot Time 1 - Time 2</option>
							<option value="SPLIT">Remove slot Time 1 - Time 2</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="submit" value="Change Slots">
		</form>
	</body>
</html>