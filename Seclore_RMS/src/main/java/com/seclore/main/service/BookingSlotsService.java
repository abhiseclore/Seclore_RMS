package com.seclore.main.service;

import java.sql.Date;
import java.sql.Time;
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
	public BookingSlots addNewBookingSlots(BookingDetails bookingDetails, Time startTime, Time endTime, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBookingSlots(int bookingId, Time startTime, Time endTime, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BookingSlots> getAllBookingSlotsByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
