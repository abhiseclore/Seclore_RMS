package com.seclore.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.seclore.main.domain.BookingDetails;



public class BookingDetailsRepository implements BookingDetailsRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate ;
	private static final String INSERTINTOBOOKINGDETAILS="insert into booking_details(user_id, room_id, description, status) Values(?,?,?,?)";
	private static final String UPDATEEXSISTINGBOOKINGDETAILS="update booking_details set user_id=?,room_id=?,description=?,status=?";
	private static final String GETBOOKINGDETAILSBYBOOKINGID="select *from (select bd.booking_id,bd.description,bd.room_id,bd.status,bd.user_id,ud.name,ud.position from booking_details bd left join user_details ud on  (bd.user_id=ud.user_id and bd.booking_id=?) where bd.booking_id=1) m left join room_details rd on m.room_id=rd.room_id and m.booking_id=1";
	@Override
	public boolean addBookingDetails(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub
		Object[] args= {bookingDetails.getUser().getUserId(),bookingDetails.getRoom().getRoomId(),bookingDetails.getDescription(),bookingDetails.getStatus()};
		int count=jdbcTemplate.update(INSERTINTOBOOKINGDETAILS, args);
		if(count>0) return true;
		return false;
	}
 
	@Override
	public BookingDetails updateExistingBookingDetails(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub
		Object[] args= {bookingDetails.getUser().getUserId(),bookingDetails.getRoom().getRoomId(),bookingDetails.getDescription(),bookingDetails.getStatus()};
		int count=jdbcTemplate.update(UPDATEEXSISTINGBOOKINGDETAILS, args);
		if(count>0) return bookingDetails;
		
		return null;
	}

	@Override
	public BookingDetails getExistingBookingDetails(int bookingId) {
		// TODO Auto-generated method stub
		try {
			BookingDetails bookingDetails=jdbcTemplate.queryForObject(GETBOOKINGDETAILSBYBOOKINGID,new BookingDetailsRowMapper(), bookingId);
			return bookingDetails;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
		
		
	}

}
