package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingSlots;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.SlotMaster;
import com.seclore.main.service.BookingDetailsService;
import com.seclore.main.service.BookingDetailsServiceInterface;
import com.seclore.main.service.RoomDetailsService;
import com.seclore.main.service.RoomDetailsServiceInterface;
import com.seclore.main.service.SlotMasterService;
import com.seclore.main.service.SlotMasterServiceInterface;

public class BookingSlotsRowMapper implements RowMapper<BookingSlots>{

	@Override
	public BookingSlots mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BookingDetailsServiceInterface bookingDetailsServiceInterface=new BookingDetailsService();
		RoomDetailsServiceInterface roomDetailsServiceInterface=new RoomDetailsService();
		SlotMasterServiceInterface slotMasterServiceInterface=new SlotMasterService();
		BookingSlots bookingSlots=new BookingSlots();
		int bookingId=rs.getInt("booking_id");
		int roomId=rs.getInt("room_id");
		int slotId=rs.getInt("slot_id");
		
		BookingDetails bookingDetails=bookingDetailsServiceInterface.getExistingBookingDetails(bookingId);
		RoomDetails roomDetails=roomDetailsServiceInterface.getOneRoomDetails(roomId);
		SlotMaster slotMaster=slotMasterServiceInterface.getSlotMasterBySlotId(slotId);
		
		bookingSlots.setBooking(bookingDetails);
		bookingSlots.setRoom(roomDetails);
		bookingSlots.setSlot(slotMaster);
		bookingSlots.setSlotActive(rs.getBoolean("is_slot_active"));
		return bookingSlots;
	}
	

}
