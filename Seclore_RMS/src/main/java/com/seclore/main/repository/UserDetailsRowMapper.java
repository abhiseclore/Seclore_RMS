package com.seclore.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seclore.main.domain.UserDetails;

public class UserDetailsRowMapper implements RowMapper<UserDetails> {

	@Override
	public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(rs.getInt("user_id"));
		userDetails.setPassword(rs.getString("password"));
		userDetails.setName(rs.getString("name"));
		userDetails.setPosition(rs.getString("position"));
		userDetails.setIsActive(rs.getBoolean("is_active"));
		return userDetails;
	}

}
