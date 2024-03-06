package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.seclore.main.domain.SlotMaster;

@Repository
public class SlotMasterRepository implements SlotMasterRepositoryInterface{

	@Override
	public List<SlotMaster> getAllSlotByStartTimeEndTime(Time startTime, Time endTime, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SlotMaster getSlotMasterBySlotId(int slotId) {
		// TODO Auto-generated method stub
		return null;
	}

}
