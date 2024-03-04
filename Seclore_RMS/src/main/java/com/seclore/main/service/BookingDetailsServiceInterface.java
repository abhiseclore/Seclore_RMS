package com.seclore.main.service;

import com.seclore.main.domain.BookingDetails;

public interface BookingDetailsServiceInterface {
	public boolean addBookingDetails(BookingDetails bookingDetails);
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails);
	public BookingDetails cancalExistingBookingDetails(BookingDetails bookingDetails);
	public BookingDetails approveBookingDetails(BookingDetails bookingDetails);
	public BookingDetails getExistingBookingDetails(int bookingID);
}
