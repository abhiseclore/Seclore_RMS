package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.SlotMaster;

public class StartEndTimeRowMapper implements RowMapper<BookingViewDetails> {

	@Override
	public BookingViewDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			BookingViewDetails viewDetails = new BookingViewDetails();
			viewDetails.setRoomDetails(new RoomDetails());
			viewDetails.setSlotMaster(new SlotMaster());
			
			BookingSlots bookingSlots = new BookingSlots();
			bookingSlots.setBooking(new BookingDetails());
			viewDetails.setBookingSlots(bookingSlots);
			
			viewDetails.getBookingSlots().getBooking().setBookingId(rs.getInt("booking_id"));
			viewDetails.getRoomDetails().setRoomId(rs.getInt("room_id"));
			viewDetails.getSlotMaster().setDate((rs.getDate("date")).toLocalDate());
			viewDetails.getSlotMaster().setStartTime(rs.getTime("start_time").toLocalTime());
			viewDetails.getSlotMaster().setEndTime(rs.getTime("end_time").toLocalTime());

			return viewDetails;
		} catch (Exception e) {
			return null;
		}
	}

}
