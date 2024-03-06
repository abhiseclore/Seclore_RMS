package com.seclore.main.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;

public interface BookingSlotsServiceInterface {
	public List<BookingSlots> addBookingSlots(List<BookingDetails> allbookingDetails, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate);
	public boolean deleteBookingSlots(List<BookingDetails> allbookingDetails, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate);
	public List<BookingSlots> getAllBookingSlotsByBookingId(int bookingId);
	public boolean deleteBookingSlotsByBookingId(int bookingId);
}
