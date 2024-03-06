package com.seclore.main.service;

import java.util.List;

import com.seclore.main.domain.BookingDetails;

public interface BookingDetailsServiceInterface {
	public int addBookingDetails(BookingDetails bookingDetails);
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails);
	public BookingDetails cancelExistingBookingDetails(BookingDetails bookingDetails);
	public BookingDetails approveBookingDetails(BookingDetails bookingDetails);
	public BookingDetails getExistingBookingDetails(int bookingID);
	public List<BookingDetails> getAllExistingBookingDetailsByUserId(int userId);
	public List<BookingDetails> getAllExistingBookingDetailsByadmin();
	
	
}
