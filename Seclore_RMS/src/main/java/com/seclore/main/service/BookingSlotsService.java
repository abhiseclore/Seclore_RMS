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
	public List<BookingSlots> addBookingSlots(List<BookingDetails> allbookingDetails, LocalTime startTime, LocalTime endTime,
			LocalDate startLocalDate, LocalDate endLocalDate) {
		// TODO Auto-generated method stub
		int size=allbookingDetails.size();
		List<BookingSlots> allBookingSlots=new ArrayList<BookingSlots>();
		
		for(int i=0; i<size; startLocalDate.plusDays(1) ) {
			allBookingSlots.add(bookingSlotsRepositoryInterface.addBookingSlots(allbookingDetails.get(i), startTime, endTime, startLocalDate));
			
		}
		
		return allBookingSlots;
	}

	@Override
	public boolean deleteBookingSlots(List<BookingDetails> allbookingDetails, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BookingSlots> getAllBookingSlotsByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBookingSlotsByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
