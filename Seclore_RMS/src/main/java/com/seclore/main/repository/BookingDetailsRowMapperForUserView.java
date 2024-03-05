package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;

public class BookingDetailsRowMapperForUserView implements RowMapper<BookingDetails> {
	public BookingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BookingDetails bookingDetails = new BookingDetails();
		RoomDetails roomDetails;

		

		int roomId = rs.getInt("room_id");
		String roomName = rs.getString("room_name");
		int capacity = rs.getInt("capacity");
		boolean hasWhiteboard = rs.getBoolean("white_board");
		boolean hasAudioVideo = rs.getBoolean("video_conferencing");
		boolean isAvailable = rs.getBoolean("is_available");
		roomDetails = new RoomDetails(roomId, roomName, capacity, hasWhiteboard, hasAudioVideo, isAvailable);

		bookingDetails.setBookingId(rs.getInt("booking_id"));
		bookingDetails.setUser(null);
		bookingDetails.setRoom(roomDetails);
		bookingDetails.setDescription(rs.getString("description"));
		bookingDetails.setStatus(rs.getString("status"));
		
		return bookingDetails;
	}
}
