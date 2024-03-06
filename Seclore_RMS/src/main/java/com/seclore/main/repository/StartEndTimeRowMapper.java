package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.BookingViewDetails;

public class StartEndTimeRowMapper implements RowMapper<BookingViewDetails> {

	@Override
	public BookingViewDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			BookingViewDetails viewDetails = new BookingViewDetails();
			viewDetails.getBookingSlots().getBooking().setBookingId(rs.getInt("booking_id"));
			viewDetails.getRoomDetails().setRoomId(rs.getInt("room_id"));
			viewDetails.getSlotMaster().setDate(rs.getDate("date"));
			viewDetails.getSlotMaster().setStartTime(rs.getTime("start_time"));
			viewDetails.getSlotMaster().setEndTime(rs.getTime("end_time"));
			return viewDetails;
		} catch (Exception e) {
			return null;
		}
	}

}
