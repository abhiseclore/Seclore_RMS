package com.seclore.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.seclore.main.domain.BookingDetails;

@Repository
public class BookingDetailsRepository implements BookingDetailsRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_INTO_BOOKING_DETAILS = "INSERT INTO booking_details (user_id, room_id, description, status) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_EXISTING_BOOKING_DETAILS = "UPDATE booking_details SET user_id=?, room_id=?, description=?, status=? WHERE booking_id=?";
	private static final String GET_ALL_BOOKING_DETAILS = "select *from (select bd.booking_id,bd.description,bd.room_id,bd.status,bd.user_id,ud.name,ud.position,ud.is_active from booking_details bd  join user_details ud on  (bd.user_id=ud.user_id ) where bd.status='BOOKED') m join room_details rd on m.room_id=rd.room_id order by m.user_id";
	private static final String GET_BOOKING_DETAILS_BY_USER_ID = "SELECT bd.booking_id, bd.description, bd.room_id, bd.status, bd.user_id, rd.capacity, rd.audio_video, rd.white_board, rd.room_name, rd.is_available FROM booking_details bd JOIN room_details rd ON rd.room_id=bd.room_id WHERE bd.user_id=? and bd.status='BOOKED'";
	private static final String GET_BOOKING_DETAILS_BY_BOOKING_ID = "SELECT bd.booking_id, bd.description, bd.room_id, bd.status, bd.user_id, rd.audio_video, rd.white_board, rd.capacity, ud.name, ud.position, ud.is_active, rd.room_name, rd.is_available FROM booking_details bd JOIN room_details rd ON rd.room_id=bd.room_id JOIN user_details ud ON ud.user_id=bd.user_id WHERE bd.booking_id=? and bd.status='BOOKED'";
	private static final String CANCEL_BOOKING_DETAILS_BY_BOOKING_ID = "UPDATE booking_details SET status = 'CANCELED' where booking_id = ?;";

	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingDetails) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_INTO_BOOKING_DETAILS,
						new String[] { "booking_id" });
				ps.setInt(1, bookingDetails.getUser().getUserId());
				ps.setInt(2, bookingDetails.getRoom().getRoomId());
				ps.setString(3, bookingDetails.getDescription());
				ps.setString(4, bookingDetails.getStatus());
				return ps;
			}
		};

		int result = jdbcTemplate.update(preparedStatementCreator, keyHolder);
		if (result > 0) {
			bookingDetails.setBookingId(keyHolder.getKey().intValue());
			return bookingDetails;
		}

		return null;
	}

	@Override
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails) {
		Object[] args = { bookingDetails.getUser().getUserId(), bookingDetails.getRoom().getRoomId(),
				bookingDetails.getDescription(), bookingDetails.getStatus(), bookingDetails.getBookingId() };
		int count = jdbcTemplate.update(UPDATE_EXISTING_BOOKING_DETAILS, args);
		if (count > 0)
			return bookingDetails;

		return null;
	}

	@Override
	public BookingDetails getExistingBookingDetailsbyBookigID(int bookingId) {
		try {
			return jdbcTemplate.queryForObject(GET_BOOKING_DETAILS_BY_BOOKING_ID, new BookingDetailsRowMapper(),
					bookingId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookingDetails> getExistingBookingDetailsbyuserid(int userId) {
		try {
			return jdbcTemplate.query(GET_BOOKING_DETAILS_BY_USER_ID, new BookingDetailsAndRoomDetailsRowMapper(),
					userId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookingDetails> getAllExistingBookingDetailsByadmin() {
		try {
			return jdbcTemplate.query(GET_ALL_BOOKING_DETAILS, new BookingDetailsRowMapper());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean cancelBookingDetails(BookingDetails bookingDetails) {
		try {
			int count = jdbcTemplate.update(CANCEL_BOOKING_DETAILS_BY_BOOKING_ID, bookingDetails.getBookingId());
			if (count > 0)
				return true;
			return false;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
