package com.seclore.main.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;

public interface BookingViewDetailsServiceInterface {
	List<RoomDetails> getAvailableRoomsBySlot(List<RoomDetails> roomList, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate);

	List<RoomDetails> getBookedRoomsBySlot(List<RoomDetails> roomList, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate);
	

	List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList);
}
