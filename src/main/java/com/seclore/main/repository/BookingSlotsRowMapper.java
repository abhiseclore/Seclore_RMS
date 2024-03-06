package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.SlotMaster;
import com.seclore.main.service.BookingDetailsServiceInterface;
import com.seclore.main.service.RoomDetailsServiceInterface;
import com.seclore.main.service.SlotMasterServiceInterface;

public class BookingSlotsRowMapper implements RowMapper<BookingSlots>{

	@Override
	public BookingSlots mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		@Autowired
		BookingDetailsServiceInterface bookingDetailsServiceInterface;
		
		@Autowired
		RoomDetailsServiceInterface roomDetailsServiceInterface;
		
		@Autowired
		SlotMasterServiceInterface slotMasterServiceInterface;
		
		BookingSlots bookingSlots=new BookingSlots();
		int bookingId=rs.getInt("booking_id");
		BookingDetails bookingDetails= bookingDetailsServiceInterface.getExistingBookingDetails(bookingId);
		bookingSlots.setBooking(bookingDetails);
		int roomId=rs.getInt("room_id");
		RoomDetails roomDetails=roomDetailsServiceInterface.getOneRoomDetails(roomId);
		bookingSlots.setRoom(roomDetails);
		int slotId=rs.getInt("slot_id");
		SlotMaster slotMaster=slotMasterServiceInterface.getSlotMasterBySlotId(slotId);
		bookingSlots.setSlot(slotMaster);
		bookingSlots.setSlotActive(rs.getBoolean("is_slot_active"));
		return bookingSlots;
	}

}
