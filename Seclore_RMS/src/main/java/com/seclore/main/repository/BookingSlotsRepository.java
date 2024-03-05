package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;

@Repository
public class BookingSlotsRepository implements BookingSlotsRepositoryInterface{

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
