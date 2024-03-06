package com.seclore.main.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seclore.main.domain.SlotMaster;
import com.seclore.main.repository.SlotMasterRepositoryInterface;

public class SlotMasterService implements SlotMasterServiceInterface{

	@Autowired
	SlotMasterRepositoryInterface slotMasterRepositoryInterface;
	
	@Override
	public List<SlotMaster> getAllSlotByStartTimeEndTime(LocalTime startTime, LocalTime endTime, LocalDate startDate) {
		// TODO Auto-generated method stub
		return slotMasterRepositoryInterface.getAllSlotByStartTimeEndTime(startTime, endTime, startDate);
	}

	@Override
	public SlotMaster getSlotMasterBySlotId(int slotId) {
		// TODO Auto-generated method stub
		return slotMasterRepositoryInterface.getSlotMasterBySlotId(slotId);
	}

}
