package com.seclore.main.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.BookingDetails;

public interface BookingDetailsServiceInterface {
	public boolean addBookingDetails(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int userId, int roomId, String description, String status);
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails);
	public boolean cancelExistingBookingDetails(BookingDetails bookingDetails);
	public BookingDetails approveBookingDetails(BookingDetails bookingDetails);
	public BookingDetails getExistingBookingDetails(int bookingID);
	public List<BookingDetails> getAllExistingBookingDetailsByUserId(int userId);
	public List<BookingDetails> getAllExistingBookingDetailsByadmin();
	public boolean cancelPartialBooking(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime, LocalTime newStartTime, LocalTime newEndTime, LocalDate date);
	
}
