package com.seclore.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.repository.BookingDetailsRepositoryInterface;

@Service
public class BookingDetailsService implements BookingDetailsServiceInterface {
	@Autowired
	private BookingDetailsRepositoryInterface bookingDetailsRepository;
	
	@Override
	public int addBookingDetails(BookingDetails bookingDetails) {
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
		return bookingDetailsRepository.getExistingBookingDetailsbyBookigID(bookingID);
	}

	@Override
	public List<BookingDetails> getAllExistingBookingDetailsByUserId(int userId) {
		// TODO Auto-generated method stub
		return bookingDetailsRepository.getExistingBookingDetailsbyuserid(userId);
		
	}

	@Override
	public List<BookingDetails> getAllExistingBookingDetailsByadmin() {
		// TODO Auto-generated method stub
		return bookingDetailsRepository.getAllExistingBookingDetailsByadmin();
		
	}

}
