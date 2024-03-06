package com.seclore.main.service;

import java.time.LocalDate;
import java.time.LocalTime;
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
	public List<RoomDetails> getBookedRoomsBySlot(List<RoomDetails> roomList, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate) {
			
			List<RoomDetails> bookedRoomList =  bookingViewDetailsRepository.getBookedRoomsBySlot(startTime, endTime, startDate, endDate);
			bookedRoomList.retainAll(roomList);
			return bookedRoomList;
	}

	@Override
	public List<RoomDetails> getAvailableRoomsBySlot(List<RoomDetails> roomList, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate) {
			List<RoomDetails> allAvailableRoomList = bookingViewDetailsRepository.getBookedRoomsBySlot(startTime, endTime, startDate, endDate);
			List<RoomDetails> requiredAvailableRoomList = roomList;
			roomList.retainAll(allAvailableRoomList);
			
			requiredAvailableRoomList.removeAll(allAvailableRoomList);
			
			return requiredAvailableRoomList;
	}

	@Override
	public List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList) {
		return bookingViewDetailsRepository.getStartEndTimeByBookingId(bookingDetailsList);
	}

}
