package com.seclore.main.service;

import java.util.List;

import com.seclore.main.domain.RoomDetails;

public interface RoomDetailsServiceInterface {
	public boolean addNewRoom(RoomDetails roomDetails);

	public boolean updateNewRoom(RoomDetails roomDetails);

	public RoomDetails getOneRoomDetails(int roomId);

	public List<RoomDetails> getAllRoomDetails();

	public List<RoomDetails> getRoomsByNameLike(String pattern);
	
	public List<RoomDetails> getAvailableRoomsWithCondition(RoomDetails roomDetails);

}
