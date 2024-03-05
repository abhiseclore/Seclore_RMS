package com.seclore.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.repository.BookingDetailsRepositoryInterface;

@Service
public class BookingDetailsService implements BookingDetailsServiceInterface {
	@Autowired
	private BookingDetailsRepositoryInterface bookingDetailsRepository;
	
	@Override
	public boolean addBookingDetails(BookingDetails bookingDetails) {
		return bookingDetailsRepository.addBookingDetails(bookingDetails);
	}

	@Override
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails) {
		return bookingDetailsRepository.updateExistingBookingDetails(bookingDetails);
	}

	@Override
	public BookingDetails cancelExistingBookingDetails(BookingDetails bookingDetails) {
		bookingDetails.setStatus("CANCELED");
		return bookingDetailsRepository.updateExistingBookingDetails(bookingDetails);
	}

	@Override
	public BookingDetails approveBookingDetails(BookingDetails bookingDetails) {
		
		bookingDetails.setStatus("BOOKED");
		return bookingDetailsRepository.updateExistingBookingDetails(bookingDetails);
	}

	@Override
	public BookingDetails getExistingBookingDetails(int bookingID) {
		return bookingDetailsRepository.getExistingBookingDetails(bookingID);
	}

}
