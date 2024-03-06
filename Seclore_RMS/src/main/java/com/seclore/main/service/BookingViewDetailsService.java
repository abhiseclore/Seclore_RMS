package com.seclore.main.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.repository.BookingViewDetailsRepositoryInterface;

@Service
public class BookingViewDetailsService implements BookingViewDetailsServiceInterface {
	
	@Autowired
	BookingViewDetailsRepositoryInterface bookingViewDetailsRepository;

	@Override
	public List<RoomDetails> getBookedRoomsBySlot(List<RoomDetails> roomList, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate) {
			
			List<RoomDetails> allBookedRoomList =  bookingViewDetailsRepository.getBookedRoomsBySlot(startTime, endTime, startDate, endDate);
			roomList.retainAll(allBookedRoomList);
			return roomList;
	}

	@Override
	public List<RoomDetails> getAvailableRoomsBySlot(List<RoomDetails> roomList, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate) {
			List<RoomDetails> allBookedRoomList = bookingViewDetailsRepository.getBookedRoomsBySlot(startTime, endTime, startDate, endDate);
			List<RoomDetails> requiredAvailableRoomList = roomList;
			roomList.retainAll(allBookedRoomList);
			
			requiredAvailableRoomList.removeAll(roomList);
			
			return requiredAvailableRoomList;
	}

	@Override
	public List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList) {
		return bookingViewDetailsRepository.getStartEndTimeByBookingId(bookingDetailsList);
	}

	@Override
	public BookingViewDetails getStartEndTimeForSingleId(BookingDetails bookingDetails) {
		List<BookingDetails> bookingDetailsList = new ArrayList<BookingDetails>();
		bookingDetailsList.add(bookingDetails);
				
		List<BookingViewDetails> bookingViewDetailsResultList = getStartEndTimeByBookingId(bookingDetailsList);
		
		if(bookingViewDetailsResultList==null || bookingViewDetailsResultList.size()==0)
				return null;
		
		return bookingViewDetailsResultList.get(0);
	}

}
