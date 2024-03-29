package com.seclore.main.domain;

import java.util.Objects;

public class RoomDetails {
	private int roomId;
	private String roomName;
	private int capacity;
	private boolean hasWhiteboard;
	private boolean hasAudioVideo;
	private boolean isAvailable;
	
	
	public RoomDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public RoomDetails(int roomId, String roomName, int capacity, boolean hasWhiteboard, boolean hasAudioVideo,
			boolean isAvailable) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.capacity = capacity;
		this.hasWhiteboard = hasWhiteboard;
		this.hasAudioVideo = hasAudioVideo;
		this.isAvailable = isAvailable;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public boolean getHasWhiteboard() {
		return hasWhiteboard;
	}
	public void setHasWhiteboard(boolean hasWhiteboard) {
		this.hasWhiteboard = hasWhiteboard;
	}
	public boolean getHasAudioVideo() {
		return hasAudioVideo;
	}
	public void setHasAudioVideo(boolean hasAudioVideo) {
		this.hasAudioVideo = hasAudioVideo;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "RoomDetails [roomId=" + roomId + ", roomName=" + roomName + ", capacity=" + capacity
				+ ", hasWhiteboard=" + hasWhiteboard + ", hasAudioVideo=" + hasAudioVideo + ", isAvailable="
				+ isAvailable + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(roomId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomDetails other = (RoomDetails) obj;
		return roomId == other.roomId;
	}
	
	
	
	
	
	
}
