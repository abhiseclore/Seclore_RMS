package com.seclore.main.repository;

import java.util.List;

import com.seclore.main.domain.BookingDetails;
public interface BookingDetailsRepositoryInterface {
	public BookingDetails addBookingDetails(BookingDetails bookingDetails);
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails);
	public List<BookingDetails> getExistingBookingDetailsbyuserid(int userId);
	public BookingDetails getExistingBookingDetailsbyBookigID(int bookingId);
	public List<BookingDetails> getAllExistingBookingDetailsByadmin();
	public boolean cancelBookingDetails(BookingDetails bookingDetails);
	
	
}
