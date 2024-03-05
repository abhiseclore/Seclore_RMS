package com.seclore.main.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.seclore.main.domain.SlotMaster;

public interface SlotMasterServiceInterface {
	public List<SlotMaster> getAllSlotByStartTimeEndTime(Time startTime, Time endTime, Date startDate, Date endDate);
	public SlotMaster getSlotMasterBySlotId(int slotId);
}
