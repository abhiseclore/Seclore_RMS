package com.seclore.main.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.SlotMaster;

public interface SlotMasterServiceInterface {
	public List<SlotMaster> getAllSlotByStartTimeEndTime(LocalTime startTime, LocalTime endTime, LocalDate localDate);
	public SlotMaster getSlotMasterBySlotId(int slotId);
}
