package com.seclore.main.repository;

import java.util.List;

import com.seclore.main.domain.RoomDetails;

public interface RoomDetailsRepositoryInterface {

	public boolean addNewRoom(String roomName, int capacity, boolean hasWhiteboard, boolean hasAudioVideo,
			boolean isAvailable);

	public boolean updateNewRoom(int roomId, String roomName, int capacity, boolean hasWhiteboard,
			boolean hasAudioVideo, boolean isAvailable);

	public RoomDetails getOneRoomDetails(int roomId);

	public List<RoomDetails> getAllRoomDetails();

	public List<RoomDetails> getRoomsByNameLike(String roomName);
}
