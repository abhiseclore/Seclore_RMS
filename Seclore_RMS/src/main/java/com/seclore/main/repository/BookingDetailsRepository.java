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
	private static final String INSERT_INTO_BOOKING_DETAILS = "insert into booking_details(user_id, room_id, description, status) Values(?,?,?,?)";
	private static final String UPDATE_EXSISTI_NGBOOKING_DETAILS = "update booking_details set user_id=?,room_id=?,description=?,status=?";
	private static final String GET_ALL_BOOKING_DETAILS = "select *from (select bd.booking_id,bd.description,bd.room_id,bd.status,bd.user_id,ud.name,ud.position from booking_details bd  join user_details ud on  (bd.user_id=ud.user_id )) m join room_details rd on m.room_id=rd.room_id order by m.user_id";
	private static final String GET_BOOKING_DETAILS_BY_USER_ID = "select bd.booking_id,bd.description,bd.room_id,bd.status,bd.user_id,rd.capacity,rd.video_conferencing,rd.white_board,rd.room_name,rd.is_available  from booking_details bd  join room_details rd on  rd.room_id=bd.room_id where bd.user_id=?";
	private static final String GET_BOOKING_DETAILS_BY_BOOKING_ID="select m.booking_id,m.description,m.room_id,m.status,m.user_id,rd.video_conferencing,rd.white_board,rd.capacity,ud.name,ud.position, ud.is_active,rd.room_name,rd.is_available from (select * from booking_details where booking_id=? ) m  join room_details rd on rd.room_id=m.room_id join user_details ud on ud.user_id=m.user_id";
	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingDetails) {
		
		// TODO Auto-generated method stub
		
		KeyHolder keyHolder = new GeneratedKeyHolder();		
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_BOOKING_DETAILS);
	            ps.setInt(1, bookingDetails.getUser().getUserId());
	            ps.setInt(2, bookingDetails.getRoom().getRoomId());
	            ps.setString(3, bookingDetails.getDescription());
	            ps.setString(4,  bookingDetails.getStatus());
	            return ps;
	        }
	    };
		int result = jdbcTemplate.update(preparedStatementCreator, keyHolder);
		if(result > 0) {
			bookingDetails.setBookingId(result);
			return bookingDetails;
		}
			
		
		
		return null;
	}
	
	
	
	

	
	

	@Override
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub
		Object[] args = { bookingDetails.getUser().getUserId(), bookingDetails.getRoom().getRoomId(),
				bookingDetails.getDescription(), bookingDetails.getStatus() };
		int count = jdbcTemplate.update(UPDATE_EXSISTI_NGBOOKING_DETAILS, args);
		if (count > 0)
			return bookingDetails;

		return null;
	}

	@Override
	public BookingDetails getExistingBookingDetailsbyBookigID(int bookingId) {
		// TODO Auto-generated method stub
		//change the row mapper- Done
		try {
			BookingDetails bookingDetails = jdbcTemplate.queryForObject(GET_BOOKING_DETAILS_BY_BOOKING_ID,
					new BookingDetailsRowMapper(), bookingId);
				System.out.println(bookingDetails);
			return bookingDetails;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}

	}


	@Override
	public List<BookingDetails> getExistingBookingDetailsbyuserid(int userId) {
		// TODO Auto-generated method stub
		try {
			List<BookingDetails> bookingDetails = jdbcTemplate.query(GET_BOOKING_DETAILS_BY_USER_ID,new BookingDetailsAndRoomDetailsRowMapper(), userId);
			return bookingDetails;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	@Override
	public List<BookingDetails> getAllExistingBookingDetailsByadmin() {
		// TODO Auto-generated method stub
		try {
			List<BookingDetails> allBookingDetailsForUserId = jdbcTemplate.query(GET_ALL_BOOKING_DETAILS,new BookingDetailsRowMapper());
			return allBookingDetailsForUserId;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;	
	}

	
}
