package com.seclore.main.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class SlotMaster {
	private int slotId;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate date;

	public SlotMaster() {
		// TODO Auto-generated constructor stub
	}

	public SlotMaster(int slotId, LocalTime startTime, LocalTime endTime, LocalDate date) {
		super();
		this.slotId = slotId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SlotMaster [slotId=" + slotId + ", startTime=" + startTime + ", endTime=" + endTime + ", date=" + date
				+ "]";
	}

}
