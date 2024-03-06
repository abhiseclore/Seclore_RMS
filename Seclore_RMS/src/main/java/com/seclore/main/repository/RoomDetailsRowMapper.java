package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.RoomDetails;

public class RoomDetailsRowMapper implements RowMapper<RoomDetails>{

	@Override
	public RoomDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setRoomId(rs.getInt("room_id"));
		roomDetails.setRoomName(rs.getString("room_name"));
		roomDetails.setIsAvailable(rs.getBoolean("is_available"));
		roomDetails.setCapacity(rs.getInt("capacity"));
		roomDetails.setHasWhiteboard(rs.getBoolean("white_board"));
		roomDetails.setHasAudioVideo(rs.getBoolean("audio_video"));
		return roomDetails;
	}

}
