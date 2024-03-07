package com.seclore.main.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;

public interface BookingSlotsServiceInterface {
	public int addBookingSlots(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime, LocalDate date);
	public boolean deleteBookingSlots(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime, LocalDate date);
	//public List<BookingSlots> getAllBookingSlotsByBookingId(int bookingId);
	//public List<BookingSlots> getAllBookingSlotsByTime(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime, LocalDate date);
	public boolean deleteBookingSlotsByBookingId(int bookingId);
}
