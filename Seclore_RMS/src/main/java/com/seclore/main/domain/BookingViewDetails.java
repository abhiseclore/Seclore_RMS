package com.seclore.main.domain;

public class BookingViewDetails {
	RoomDetails roomDetails;
	SlotMaster slotMaster;
	BookingSlots bookingSlots;
	boolean isBooked;
	
	public BookingViewDetails() {
		// TODO Auto-generated constructor stub
	}

	public BookingViewDetails(RoomDetails roomDetails, SlotMaster slotMaster, BookingSlots bookingSlots,
			boolean isBooked) {
		super();
		this.roomDetails = roomDetails;
		this.slotMaster = slotMaster;
		this.bookingSlots = bookingSlots;
		this.isBooked = isBooked;
	}

	
	
	public RoomDetails getRoomDetails() {
		return roomDetails;
	}

	public void setRoomDetails(RoomDetails roomDetails) {
		this.roomDetails = roomDetails;
	}

	public SlotMaster getSlotMaster() {
		return slotMaster;
	}

	public void setSlotMaster(SlotMaster slotMaster) {
		this.slotMaster = slotMaster;
	}

	public BookingSlots getBookingSlots() {
		return bookingSlots;
	}

	public void setBookingSlots(BookingSlots bookingSlots) {
		this.bookingSlots = bookingSlots;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	@Override
	public String toString() {
		return "BookingViewDetails [roomDetails=" + roomDetails + ", slotMaster=" + slotMaster + ", bookingSlots="
				+ bookingSlots + ", isBooked=" + isBooked + "]";
	}
	
}