package com.seclore.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seclore.main.domain.RoomDetails;

@Repository
public class RoomDetailsRepository implements RoomDetailsRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String INSERT_ROOM = "INSERT INTO room_details(room_name,capacity, audio_video, white_board,is_available) VALUES(?,?,?,?,?);";
	private String UPDATE_ROOM = "UPDATE room_details SET room_name=? AND capacity=? ANd audio_video=? AND white_board=? AND is_available=? WHERE room_id=?;";
	private String SELECT_ROOM = "SELECT * FROM room_details WHERE room_id=?;";
	private String SELECT_ALL_ROOMS = "SELECT * FROM room_details;";
	private String SELECT_ALL_ROOMS_LIKE = "SELECT * FROM room_details WHERE room_name LIKE ?;";
	private String SELECT_AVAILABLE_ROOMS = "SELECT * FROM room_details WHERE capacity > ? AND white_board >= ? AND audio_video >= ? AND is_available = 1;";
	
	@Override
	public boolean addNewRoom(RoomDetails roomDetails) {
		Object[] args = { roomDetails.getRoomName(), roomDetails.getCapacity(), roomDetails.getHasAudioVideo(),
				roomDetails.getHasWhiteboard(), roomDetails.getIsAvailable() };
		int rowCount = jdbcTemplate.update(INSERT_ROOM, args);
		if (rowCount > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateRoom(RoomDetails roomDetails) {
		Object[] args = { roomDetails.getRoomId(), roomDetails.getRoomName(), roomDetails.getCapacity(),
				roomDetails.getHasAudioVideo(), roomDetails.getHasWhiteboard(), roomDetails.getIsAvailable() };
		int rowCount = jdbcTemplate.update(UPDATE_ROOM, args);
		if (rowCount > 0)
			return true;
		return false;
	}

	@Override
	public RoomDetails getOneRoomDetails(int roomId) {
		RoomDetails roomDetails = jdbcTemplate.queryForObject(SELECT_ROOM, new RoomDetailsRowMapper(), roomId);
		return roomDetails;
	}

	@Override
	public List<RoomDetails> getAllRoomDetails() {
		List<RoomDetails> allRooms = jdbcTemplate.query(SELECT_ALL_ROOMS, new RoomDetailsRowMapper());
		return allRooms;
	}

	@Override
	public List<RoomDetails> getRoomsByNameLike(String roomPattern) {
		String pattern = "%" + roomPattern + "%";
		List<RoomDetails> allRoomsWithPattern = jdbcTemplate.query(SELECT_ALL_ROOMS_LIKE,
				new RoomDetailsRowMapper(),pattern);
		return allRoomsWithPattern;
	}

	@Override
	public List<RoomDetails> getAvailableRoomsWithCondition(RoomDetails roomDetails) {
		Object[] args = {
			roomDetails.getCapacity(),
			roomDetails.getHasWhiteboard(),
			roomDetails.getHasAudioVideo()
		};
		List<RoomDetails> allRooms = jdbcTemplate.query(SELECT_AVAILABLE_ROOMS, new RoomDetailsRowMapper(),args);
		return allRooms;
	}

}
