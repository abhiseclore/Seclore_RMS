package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.SlotMaster;

public class SlotMasterRowMapper implements RowMapper<SlotMaster>{

	@Override
	public SlotMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		SlotMaster slotMaster=new SlotMaster();
		slotMaster.setSlotId(rs.getInt("slot_id"));
		slotMaster.setStartTime(rs.getTime("start_time"));
		slotMaster.setEndTime(rs.getTime("end_time"));
		slotMaster.setDate(rs.getDate("date"));
		return slotMaster;
	}
	
}
