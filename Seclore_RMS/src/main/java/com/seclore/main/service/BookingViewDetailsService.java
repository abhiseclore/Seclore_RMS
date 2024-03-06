package com.seclore.main.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.repository.BookingViewDetailsRepositoryInterface;

public class BookingViewDetailsService implements BookingViewDetailsServiceInterface {
	
	@Autowired
	BookingViewDetailsRepositoryInterface bookingViewDetailsRepository;

	@Override
	public List<RoomDetails> getBookedRoomsBySlot(List<RoomDetails> roomList, Time startTime, Time endTime,
			Date startDate, Date endDate) {
			
			List<RoomDetails> bookedRoomList =  bookingViewDetailsRepository.getBookedRoomsBySlot(startTime, endTime, startDate, endDate);
			bookedRoomList.retainAll(roomList);
			return bookedRoomList;
	}

	@Override
	public List<RoomDetails> getAvailableRoomsBySlot(List<RoomDetails> roomList, Time startTime, Time endTime,
			Date startDate, Date endDate) {
			List<RoomDetails> commonRoomsList = bookingViewDetailsRepository.getBookedRoomsBySlot(startTime, endTime, startDate, endDate);
			commonRoomsList.retainAll(roomList);
			
			List<RoomDetails> availableRoomList = roomList;
			availableRoomList.removeAll(commonRoomsList);
			
			return availableRoomList;
	}

	@Override
	public List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList) {
		return bookingViewDetailsRepository.getStartEndTimeByBookingId(bookingDetailsList);
	}

}