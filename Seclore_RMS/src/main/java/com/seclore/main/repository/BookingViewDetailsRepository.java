package com.seclore.main.repository;

import java.sql.Date;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;

@Repository
public class BookingViewDetailsRepository implements BookingViewDetailsRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String GET_ROOM_STATUS = "";
	private static final String GET_STARTEND_TIME = "";
	@Override
	public List<RoomDetails> getBookedRoomsBySlot(Time startTime, Time endTime, Date startDate, Date endDate) {
		Object[] args = {startTime,endTime,startDate,endDate};
		List<RoomDetails> roomDetailsList = jdbcTemplate.queryForObject(GET_ROOM_STATUS,new BookingRowMapper(), args);
		
		return roomDetailsList;
			
	}
	@Override
	public List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList) {
		Object[] args = {};
		List<BookingViewDetails> bookingViewDetails = jdbcTemplate.queryForObject(GET_STARTEND_TIME, new StartEndTimeRowMapper(),args);
		
		return bookingViewDetails;

	}
	

}
