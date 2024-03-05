package com.seclore.main.domain;

public class BookingSlots {
	private BookingDetails booking;
	private SlotMaster slot;
	private RoomDetails room;
	private boolean isSlotActive;
	public BookingSlots() {
		// TODO Auto-generated constructor stub
	}
	public BookingSlots(BookingDetails booking, SlotMaster slot, RoomDetails room, boolean isSlotActive) {
		super();
		this.booking = booking;
		this.slot = slot;
		this.room = room;
		this.isSlotActive = isSlotActive;
	}
	public BookingDetails getBooking() {
		return booking;
	}
	public void setBooking(BookingDetails booking) {
		this.booking = booking;
	}
	public SlotMaster getSlot() {
		return slot;
	}
	public void setSlot(SlotMaster slot) {
		this.slot = slot;
	}
	public RoomDetails getRoom() {
		return room;
	}
	public void setRoom(RoomDetails room) {
		this.room = room;
	}
	public boolean isSlotActive() {
		return isSlotActive;
	}
	public void setSlotActive(boolean isSlotActive) {
		this.isSlotActive = isSlotActive;
	}
	@Override
	public String toString() {
		return "BookingSlots [booking=" + booking + ", slot=" + slot + ", room=" + room + ", isSlotActive="
				+ isSlotActive + "]";
	}
}
