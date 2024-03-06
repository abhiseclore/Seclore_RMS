package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;
import com.seclore.main.domain.RoomDetails;

public interface BookingSlotsRepositoryInterface {
	public BookingSlots addNewBookingSlots(BookingDetails bookingDetails, Time startTime, Time endTime, Date startDate, Date endDate);
	public boolean deleteBookingSlots(int bookingId, Time startTime, Time endTime, Date startDate, Date endDate);
	public List<BookingSlots> getAllBookingSlotsByBookingId(int bookingId);
}
