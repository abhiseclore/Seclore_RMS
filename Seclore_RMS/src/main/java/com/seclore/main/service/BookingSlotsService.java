package com.seclore.main.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;
import com.seclore.main.repository.BookingSlotsRepositoryInterface;

@Service
public class BookingSlotsService implements BookingSlotsServiceInterface{

	@Autowired
	BookingSlotsRepositoryInterface bookingSlotsRepositoryInterface;

	@Override
	public int addBookingSlots(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime,
			LocalDate date) {
		// TODO Auto-generated method stub
		return bookingSlotsRepositoryInterface.addBookingSlots(bookingDetails, startTime, endTime, date);
	}

	@Override
	public boolean deleteBookingSlots(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime,
			LocalDate date) {
		// TODO Auto-generated method stub
		return bookingSlotsRepositoryInterface.deleteBookingSlots(bookingDetails, startTime, endTime, date);
	}

//	@Override
//	public List<BookingSlots> getAllBookingSlotsByBookingId(int bookingId) {
//		// TODO Auto-generated method stub
//		return bookingSlotsRepositoryInterface.getAllBookingSlotsByBookingId(bookingId);
//	}

//	@Override
//	public List<BookingSlots> getAllBookingSlotsByTime(BookingDetails bookingDetails, LocalTime startTime,
//			LocalTime endTime, LocalDate date) {
//		// TODO Auto-generated method stub
//		return bookingSlotsRepositoryInterface.getAllBookingSlotsByTime(bookingDetails, startTime, endTime, date);
//	}

	@Override
	public boolean deleteBookingSlotsByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		return bookingSlotsRepositoryInterface.deleteBookingSlotsByBookingId(bookingId);
	}
	

}
