package com.seclore.main.domain;

public class BookingDetails {
	private int bookingId;
	private RoomDetails room;
	private UserDetails user;
	private String description;
	private String status;
	
	public BookingDetails() {
		// TODO Auto-generated constructor stub
	}

	public BookingDetails(int bookingId, RoomDetails room, UserDetails user, String description, String status) {
		super();
		this.bookingId = bookingId;
		this.room = room;
		this.user = user;
		this.description = description;
		this.status = status;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public RoomDetails getRoom() {
		return room;
	}

	public void setRoom(RoomDetails room) {
		this.room = room;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", description=" + description + ", room=" + room
				+ ", status=" + status + ", user=" + user + "]";
	}
	
	
	
	
	
	
	
}
