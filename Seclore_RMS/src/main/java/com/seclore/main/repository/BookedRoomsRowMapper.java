package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.RoomDetails;

public class BookedRoomsRowMapper implements RowMapper<RoomDetails> {

	@Override
	public RoomDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			RoomDetails details = new RoomDetails();
			int roomId = rs.getInt("room_id");
			details.setRoomId(roomId);
			return details;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
