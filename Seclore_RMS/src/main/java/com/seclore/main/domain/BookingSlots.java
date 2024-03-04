package com.seclore.main.domain;

public class BookingSlots {
	private int bookingId;
	private int slotId;
	private boolean isSlotActive;
	public BookingSlots() {
		// TODO Auto-generated constructor stub
	}
	public BookingSlots(int bookingId, int slotId, boolean isSlotActive) {
		super();
		this.bookingId = bookingId;
		this.slotId = slotId;
		this.isSlotActive = isSlotActive;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	public boolean getIsSlotActive() {
		return isSlotActive;
	}
	public void setIsSlotActive(boolean isSlotActive) {
		this.isSlotActive = isSlotActive;
	}
	@Override
	public String toString() {
		return "BookingSlots [bookingId=" + bookingId + ", slotId=" + slotId + ", isSlotActive=" + isSlotActive + "]";
	}
	
	
}
