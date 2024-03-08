package com.seclore.main.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;
import com.seclore.main.repository.BookingDetailsRepositoryInterface;
import com.seclore.main.repository.BookingSlotsRepositoryInterface;

@Service
public class BookingDetailsService implements BookingDetailsServiceInterface {
	@Autowired
	private BookingDetailsRepositoryInterface bookingDetailsRepository;

	@Autowired
	private BookingSlotsRepositoryInterface bookingSlotsRepository;

	@Override
	public List<BookingDetails> addBookingDetails(LocalDate startDate, LocalDate endDate, LocalTime startTime,
			LocalTime endTime, int userId, int roomId, String description) {

		List<BookingDetails> batchBookings = new ArrayList<BookingDetails>();
//		BookingDetails booking;

		do {
			BookingDetails bookingDetails = new BookingDetails();
			bookingDetails.setRoom(new RoomDetails());
			bookingDetails.getRoom().setRoomId(roomId);
			bookingDetails.setUser(new UserDetails());
			bookingDetails.getUser().setUserId(userId);
			bookingDetails.setDescription(description);
			bookingDetails.setStatus("BOOKED");
//			BookingDetails	booking = bookingDetailsRepository.addBookingDetails(bookingDetails);
//		System.out.println(booking);
			batchBookings.add(bookingDetailsRepository.addBookingDetails(bookingDetails));

//			bookingSlotsRepository.addBookingSlots(booking, startTime, endTime, startDate);
			startDate = startDate.plusDays(1);
		} while (!startDate.isAfter(endDate));
		System.out.println(batchBookings);

		for (BookingDetails booking : batchBookings) {
			bookingSlotsRepository.addBookingSlots(booking, startTime, endTime, startDate);
		}

		return batchBookings;
	}

	@Override
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails) {
		return bookingDetailsRepository.updateExistingBookingDetails(bookingDetails);
	}

	@Override
	public boolean cancelExistingBookingDetails(BookingDetails bookingDetails) {
		return bookingDetailsRepository.cancelBookingDetails(bookingDetails);

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

	@Override
	@Transactional
	public boolean cancelPartialBooking(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime,
			LocalTime newStartTime, LocalTime newEndTime, LocalDate date, String action) {

		BookingDetails booking;

		if (action.equals("SHRINK")) {
			bookingSlotsRepository.deleteBookingSlots(bookingDetails, startTime, newStartTime, date);
			bookingSlotsRepository.deleteBookingSlots(bookingDetails, newEndTime, endTime, date);
		}
		if (action.equals("SPLIT")) {
			cancelExistingBookingDetails(bookingDetails);

			booking = bookingDetailsRepository.addBookingDetails(bookingDetails);
			bookingSlotsRepository.addBookingSlots(booking, startTime, newStartTime, date);

			booking = bookingDetailsRepository.addBookingDetails(bookingDetails);
			bookingSlotsRepository.addBookingSlots(booking, startTime, endTime, date);
		}
		return true;
	}
}
