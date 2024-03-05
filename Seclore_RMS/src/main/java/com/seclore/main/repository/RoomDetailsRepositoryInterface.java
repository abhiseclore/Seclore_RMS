package com.seclore.main.repository;

import java.util.List;

import com.seclore.main.domain.RoomDetails;

public interface RoomDetailsRepositoryInterface {

	public boolean addNewRoom(RoomDetails roomDetails);

	public boolean updateNewRoom(RoomDetails roomDetails);

	public RoomDetails getOneRoomDetails(int roomId);

	public List<RoomDetails> getAllRoomDetails();

	public List<RoomDetails> getRoomsByNameLike(String roomName);
}
