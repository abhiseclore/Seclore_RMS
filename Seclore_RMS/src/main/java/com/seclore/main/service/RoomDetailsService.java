package com.seclore.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seclore.main.domain.RoomDetails;
import com.seclore.main.repository.RoomDetailsRepositoryInterface;

@Service
public class RoomDetailsService implements RoomDetailsServiceInterface{

	@Autowired
	private RoomDetailsRepositoryInterface roomDetailsRepository;

	@Override
	public boolean addNewRoom(RoomDetails roomDetails) {
		return roomDetailsRepository.addNewRoom(roomDetails);
	}

	@Override
	public boolean updateNewRoom(RoomDetails roomDetails) {
		return roomDetailsRepository.updateNewRoom(roomDetails);
	}

	@Override
	public RoomDetails getOneRoomDetails(int roomId) {
		return roomDetailsRepository.getOneRoomDetails(roomId);
	}

	@Override
	public List<RoomDetails> getAllRoomDetails() {
		return roomDetailsRepository.getAllRoomDetails();
	}

	@Override
	public List<RoomDetails> getRoomsByNameLike(String roomName) {
		return roomDetailsRepository.getRoomsByNameLike(roomName);
	}
	
	

}
