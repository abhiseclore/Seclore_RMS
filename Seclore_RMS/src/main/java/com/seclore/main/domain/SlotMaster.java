package com.seclore.main.domain;

import java.sql.Date;
import java.sql.Time;

public class SlotMaster {
	private int slotId;
	private Time startTime;
	private Time endTime;
	private Date date;
	public SlotMaster() {
		// TODO Auto-generated constructor stub
	}
	public SlotMaster(int slotId, Time startTime, Time endTime, Date date) {
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
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "SlotMaster [slotId=" + slotId + ", startTime=" + startTime + ", endTime=" + endTime + ", date=" + date
				+ "]";
	}
	
	
}
