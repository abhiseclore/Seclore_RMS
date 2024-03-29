package com.seclore.main.repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seclore.main.domain.SlotMaster;

@Repository
public class SlotMasterRepository implements SlotMasterRepositoryInterface{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static String GET_ALL_SLOT_BY_STARTTIME_ENDTIME="DECLARE @date DATE =? "
			+ "DECLARE @startTime Time =? "
			+ "DECLARE @endTime Time =? "
			+ "select * from slot_master "
			+ "where ( ((start_time>@startTime) and (end_time<@endTime) and (end_time!='00:00:00')) "
			+ "or ( start_time<=@startTime and end_time>@startTime) "
			+ "or ( start_time<@endTime and (end_time>=@endTime or end_time='00:00:00')) ) "
			+ "and date=@date;";
	
	private static String GET_SLOT_BY_ID="select * from slot_master where slot_id=?";
	
	@Override
	public List<SlotMaster> getAllSlotByStartTimeEndTime(LocalTime startLocalTime, LocalTime endLocalTime, LocalDate localDate) {
		// TODO Auto-generated method stub
		
		startLocalTime.minusMinutes(30);
		endLocalTime.plusMinutes(30);
		
		Time startedTime=Time.valueOf(startLocalTime);
		Time endedTime=Time.valueOf(endLocalTime);
		
		Date date=Date.valueOf(localDate);
		
		
		Object[] args= {date,startedTime,endedTime};
		List<SlotMaster> allSlotMasters=jdbcTemplate.query(GET_ALL_SLOT_BY_STARTTIME_ENDTIME, new SlotMasterRowMapper(), args);
		return allSlotMasters;
	}

	@Override
	public SlotMaster getSlotMasterBySlotId(int slotId) {
		// TODO Auto-generated method stub
		SlotMasterRowMapper slotMasterRowMapper=new SlotMasterRowMapper();
		SlotMaster slotMaster=jdbcTemplate.queryForObject(GET_SLOT_BY_ID, slotMasterRowMapper, slotId);
		return slotMaster;
	}

}
