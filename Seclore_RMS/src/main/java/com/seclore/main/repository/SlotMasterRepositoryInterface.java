package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.seclore.main.domain.SlotMaster;

public interface SlotMasterRepositoryInterface {
	public List<SlotMaster> getAllSlotIdByStartTimeEndTime(Time startTime, Time endTime, Date startDate, Date endDate);
}
