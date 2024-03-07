package com.seclore.main.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

	private static final String GET_ROOM_STATUS = "exec getBookedRooms ?, ?, ?, ?";
	private static final String GET_STARTEND_TIME = "exec getStartEndTime ?";
	private static final String CHECK_AVAILABILITY = "declare @startTime time = ?\r\n" + "declare @endTime time = ?\r\n"
			+ "declare @startDate date = ?\r\n" + "declare @endDate date = ?\r\n"
			+ "select Count(room_id) as room_id from all_bookings where room_id = ? and is_booked = 1 and (start_time between @startTime and @endTime) and (end_time between @startTime and @endTime) and (date between @startDate and @endDate)\r\n";

	@Override
	public List<RoomDetails> getBookedRoomsBySlot(LocalTime startTime, LocalTime endTime, LocalDate startDate,
			LocalDate endDate) {
		Object[] args = { startTime, endTime, startDate, endDate };
		List<RoomDetails> roomDetailsList = jdbcTemplate.query(GET_ROOM_STATUS, new BookedRoomsRowMapper(), args);

		return roomDetailsList;

	}

	@Override
	public List<BookingViewDetails> getStartEndTimeByBookingId(List<BookingDetails> bookingDetailsList) {
		List<BookingViewDetails> bookingStartEndTimeList = new ArrayList<BookingViewDetails>();

		for (BookingDetails details : bookingDetailsList) {
			List<BookingViewDetails> bookingViewList = jdbcTemplate.query(GET_STARTEND_TIME,
					new StartEndTimeRowMapper(), details.getBookingId());
			if (bookingViewList.size() == 1)
				bookingStartEndTimeList.add(bookingViewList.get(0));
			else {
				bookingViewList.get(0).getSlotMaster().setEndTime(bookingViewList.get(1).getSlotMaster().getEndTime());
				bookingStartEndTimeList.add(bookingViewList.get(0));
			}
		}

		return bookingStartEndTimeList;

	}

	@Override
	public boolean checkRoomAvailabilityBySlot(RoomDetails roomDetails, LocalTime startTime, LocalTime endTime,
			LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub

		Object[] args = { startTime, endTime, startDate, endDate, roomDetails.getRoomId() };
		RoomDetails checkRoomDetails = jdbcTemplate.queryForObject(CHECK_AVAILABILITY, new BookedRoomsRowMapper(),
				args);
		return (checkRoomDetails.getRoomId() == 0);
	}

}
