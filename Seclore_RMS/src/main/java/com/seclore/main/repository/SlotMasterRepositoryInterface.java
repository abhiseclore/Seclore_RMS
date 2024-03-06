package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.seclore.main.domain.SlotMaster;

public interface SlotMasterRepositoryInterface {
	public List<SlotMaster> getAllSlotByStartTimeEndTime(LocalTime startLocalTime, LocalTime endLocalTime, LocalDate localDate);
	public SlotMaster getSlotMasterBySlotId(int slotId);
}
