package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;

public class BookingDetailsRowMapper implements RowMapper<BookingDetails> {

	public BookingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BookingDetails bookingDetails = new BookingDetails();
		UserDetails userDetails;
		RoomDetails roomDetails;

		int userId = rs.getInt("user_id");
		String userName = rs.getString("name");
		String userPosition = rs.getString("position");
		boolean userIsActive = rs.getBoolean("is_active");
		userDetails = new UserDetails(userId, userName, userPosition, userIsActive);

		int roomId = rs.getInt("room_id");
		String roomName = rs.getString("room_name");
		int capacity = rs.getInt("capacity");
		boolean hasWhiteboard = rs.getBoolean("white_board");
		boolean hasAudioVideo = rs.getBoolean("audio_video");
		boolean isAvailable = rs.getBoolean("is_available");
		roomDetails = new RoomDetails(roomId, roomName, capacity, hasWhiteboard, hasAudioVideo, isAvailable);

		bookingDetails.setBookingId(rs.getInt("booking_id"));
		bookingDetails.setUser(userDetails);
		bookingDetails.setRoom(roomDetails);
		bookingDetails.setDescription(rs.getString("description"));
		bookingDetails.setStatus(rs.getString("status"));
		
		return bookingDetails;
	}

}
