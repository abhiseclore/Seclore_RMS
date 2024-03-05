package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.BookingSlots;

public class BookingSlotsRowMapper implements RowMapper<BookingSlots>{

	@Override
	public BookingSlots mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BookingSlots bookingSlots=new BookingSlots();
		bookingSlots.se
		return null;
	}

}
