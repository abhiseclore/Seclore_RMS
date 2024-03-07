package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;
import com.seclore.main.domain.RoomDetails;

public interface BookingSlotsRepositoryInterface {
	public int addBookingSlots(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime, LocalDate date);
	public boolean deleteBookingSlots(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime, LocalDate date);
	//public List<BookingSlots> getAllBookingSlotsByBookingId(int bookingId);
	//public List<BookingSlots> getAllBookingSlotsByTime(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime, LocalDate date);
	public boolean deleteBookingSlotsByBookingId(int bookingId);
	//Changes 2
}
