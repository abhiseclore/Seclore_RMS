package com.seclore.main.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;

public interface BookingViewDetailsRepositoryInterface {
	public List<RoomDetails> getBookedRoomsBySlot(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate);
	
	List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList);
}
