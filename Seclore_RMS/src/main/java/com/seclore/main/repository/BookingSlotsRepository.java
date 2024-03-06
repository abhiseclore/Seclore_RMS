package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;

@Repository
public class BookingSlotsRepository implements BookingSlotsRepositoryInterface{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static String ADD_BOOKING_SLOT="DECLARE @date DATE = ?; "
			+ "DECLARE @startTime Time =? ;"
			+ "DECLARE @endTime Time =? ;"
			+ "DECLARE @roomId INT=?; "
			+ "DECLARE @bookingId INT=?; "
			+ "insert into booking_slots(booking_id,slot_id,room_id) ((select @bookingId, slot_id, @roomId from slot_master "
			+ "where ( ((start_time>@startTime) and (end_time<@endTime) and (end_time!='00:00:00')) "
			+ "or ( start_time<=@startTime and end_time>@startTime) "
			+ "or ( start_time<@endTime and (end_time>=@endTime or end_time='00:00:00')) ) "
			+ "and date=@date"
			+ "));";
	
	private static String DELETE_BOOKING_SLOT="DECLARE @bookingId INT=?; "
			+"DECLARE @date DATE = ?; "
			+ "DECLARE @startTime Time =?; "
			+ "DECLARE @endTime Time =?; "
			+"update booking_slots "
			+ "set is_slot_active=1 "
			+ "where booking_id=@bookingId and slot_id in "
			+ "(select slot_id from slot_master where (start_time>@startTime and end_time<@endTime) and date=@date);";
	
	public static String GET_ALL_BOOKING_SLOTS_BY_BOOKING_ID="select * from booking_slots where booking_id=?";
	
	public static String DELETE_BOOKING_SLOT_BY_BOOKING_ID="update booking_slots "
			+ "set is_slot_active=1 "
			+ "where booking_id=?;";

	@Override
	public List<BookingSlots> addBookingSlots(BookingDetails bookingDetails, LocalTime startLocalTime, LocalTime endLocalTime,
			LocalDate localDate) {
		// TODO Auto-generated method stub
		Date date=Date.valueOf(localDate);
		Time startTime=Time.valueOf(startLocalTime);
		Time endTime=Time.valueOf(endLocalTime);
		int bookingId=bookingDetails.getBookingId();
		int roomId=bookingDetails.getRoom().getRoomId();
		
		Object[] args= {date, startTime, endTime, bookingId, roomId};
		int count=jdbcTemplate.update(ADD_BOOKING_SLOT, args);
		
		if(count>0) {
			return getAllBookingSlotsByBookingId(bookingId);
		}
		return null;
	}

	@Override
	public boolean deleteBookingSlots(BookingDetails bookingDetails, LocalTime startTime, LocalTime endTime,
			LocalDate date) {
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
