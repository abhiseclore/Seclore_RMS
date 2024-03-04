package com.seclore.main.repository;

import com.seclore.main.domain.BookingDetails;
public interface BookingDetailsRepositoryInterface {
	public boolean addBookingDetails(BookingDetails bookingDetails);
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails);
	public BookingDetails getExistingBookingDetails(int bookingId);
}
