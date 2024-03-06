package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;

public interface BookingViewDetailsRepositoryInterface {
	List<RoomDetails> getBookedRoomsBySlot(Time startTime,Time endTime,Date startDate,Date endDate);
	
	List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList);
}
